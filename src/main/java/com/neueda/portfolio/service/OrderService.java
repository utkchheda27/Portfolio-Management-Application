package com.neueda.portfolio.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neueda.portfolio.repo.CashflowRepo;
import com.neueda.portfolio.repo.InstrumentRepo;
import com.neueda.portfolio.repo.OrderRepo;
import com.neueda.portfolio.entity.*;
import com.neueda.portfolio.repo.CashflowRepo;
import com.neueda.portfolio.repo.InstrumentRepo;
import com.neueda.portfolio.repo.OrderRepo;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private InstrumentRepo instrumentRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CashflowRepo cashflowRepo;

    public List<Cashflow> getCashFlow() {
        return cashflowRepo.findAll();

    }

    public List<Instrument> gettradebook() {
        return instrumentRepo.findAll().stream()
                .filter(instrument -> "stock".equalsIgnoreCase(instrument.getType()))
                .collect(Collectors.toList());

    }

    public List<Instrument> gettradebookBytickerSymbol(String tickerSymbol) {
        return instrumentRepo.findBytickerSymbol(tickerSymbol);
    }

    public List<Orders> getOrders() {
        return orderRepo.findAll();
    }

    public List<Orders> getOrdersBytickerSymbol(String tickerSymbol) {
        return orderRepo.findBytickerSymbol(tickerSymbol);

    }

    public List<Cashflow> getCashflowbytickerSymbol(String tickerSymbol) {
        return cashflowRepo.findBytickerSymbol(tickerSymbol);
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public void processOrder(String tickerSymbol, int volume, String action, Date transactionDate) throws IOException {

        if (transactionDate == null) {
            transactionDate = new Date();
        }

        // Retrieve the instrument from the Repository
        Instrument instrument = instrumentRepo.findById(tickerSymbol).orElse(null);
        double pricePerShare = getPricePerShareFromFile(tickerSymbol);

        // Validate and process the order based on the action
        if ("Sell".equalsIgnoreCase(action)) {
            if (instrument == null) {
                throw new RuntimeException("Cannot sell an instrument that is not held");
            }
            if (volume > instrument.getVolume()) {
                throw new RuntimeException("Cannot sell more than the held volume");
            }
        }

        // Initialize a new instrument if not found
        if (instrument == null) {
            if ("Sell".equalsIgnoreCase(action)) {
                throw new RuntimeException("Cannot sell an instrument that is not held");
            }
            instrument = new Instrument();
            instrument.setTickerSymbol(tickerSymbol);
            instrument.setCompanyName(getCompanyNameFromFile(tickerSymbol));
            instrument.setVolume(volume);
            instrument.setAverageBuyPrice(pricePerShare);
        } else {
            if ("Buy".equalsIgnoreCase(action)) {
                int totalVolume = instrument.getVolume() + volume;
                //double newAveragePrice = ((instrument.getAverageBuyPrice() * instrument.getVolume()) + (volume * pricePerShare)) / totalVolume;
                //instrument.setAverageBuyPrice(newAveragePrice);
                instrument.setVolume(totalVolume);
            } else if ("Sell".equalsIgnoreCase(action)) {
                instrument.setVolume(instrument.getVolume() - volume);
            }
        }
        instrumentRepo.save(instrument);

        // Create and save the order
        Orders order = new Orders();
        order.setTickerSymbol(tickerSymbol);
        order.setVolume(volume);
        order.setAction(action);
        order.setPricePerShare(pricePerShare);
        order.setTotalMoney(volume * pricePerShare);
        order.setTransactionDate(transactionDate);
        order.setInstrument(instrument);
        orderRepo.save(order);

        // Calculate and save PNL if selling
        if ("Sell".equalsIgnoreCase(action)) {
            double buyPrice = instrument.getAverageBuyPrice();
            double sellPrice = pricePerShare;
            double pnl = (sellPrice - buyPrice) * volume;
            Cashflow cashflow = new Cashflow();
            cashflow.setTickerSymbol(tickerSymbol);
            cashflow.setPnl(pnl);
            cashflow.setTransactionDate(transactionDate);
            cashflow.setInstrument(instrument);
            cashflow.setVolume(volume);
            cashflowRepo.save(cashflow);
        }
    }

    public double getPricePerShareFromFile(String tickerSymbol) throws IOException {
        Map<String, Map<String, Object>> data = readPriceDataFromClassPath();
        Map<String, Object> companyData = data.get(tickerSymbol);
        if (companyData != null && companyData.containsKey("pricePerShare")) {
            return ((Number) companyData.get("pricePerShare")).doubleValue();
        }
        throw new RuntimeException("Price data not found for ticker symbol: " + tickerSymbol);
    }

    String getCompanyNameFromFile(String tickerSymbol) throws IOException {
        Map<String, Map<String, Object>> data = readPriceDataFromClassPath();
        Map<String, Object> companyData = data.get(tickerSymbol);
        if (companyData != null && companyData.containsKey("company")) {
            return (String) companyData.get("company");
        }
        throw new RuntimeException("Company data not found for ticker symbol: " + tickerSymbol);
    }

    private Map<String, Map<String, Object>> readPriceDataFromClassPath() throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mapping.json")) {
            if (inputStream == null) {
                throw new RuntimeException("File not found in classpath: mapping.json");
            }
            return objectMapper.readValue(inputStream, Map.class);
        }
    }


    public List<OrderSummary> getOrderSummaries() throws IOException {
        List<OrderSummary> orderSummaries = new ArrayList<>();
        List<Orders> orders = orderRepo.findAll();

        for (Orders order : orders) {
            Instrument instrument = order.getInstrument();
            String tickerSymbol = order.getTickerSymbol();
            double currentMarketPrice = getPricePerShareFromFile(tickerSymbol);
            int volume = instrument.getVolume();
            double boughtPrice = instrument.getAverageBuyPrice();
            double currentMarketValue = currentMarketPrice * volume;
            double pnl = calculatePNL(instrument, currentMarketPrice);
            double pnlPercentage = calculatePNLPercentage(pnl, boughtPrice, volume);

            OrderSummary orderSummary = new OrderSummary();
            orderSummary.setInstrumentName(instrument.getCompanyName());
            orderSummary.setDateOfPurchase(order.getTransactionDate());
            orderSummary.setVolume(volume);
            orderSummary.setBoughtPrice(boughtPrice);
            orderSummary.setCurrentMarketPrice(currentMarketPrice);
            orderSummary.setCurrentMarketValue(currentMarketValue);
            orderSummary.setPnl(pnl);
            orderSummary.setPnlPercentage(pnlPercentage);

            orderSummaries.add(orderSummary);

            // Print to console
            // System.out.println("Order Summary: " + orderSummary);
        }

        return orderSummaries;


    }

    private double calculatePNL(Instrument instrument, double currentMarketPrice) {
        return (currentMarketPrice - instrument.getAverageBuyPrice()) * instrument.getVolume();
    }

    private double calculatePNLPercentage(double pnl, double boughtPrice, int volume) {
        double totalInvestment = boughtPrice * volume;
        return (pnl / totalInvestment) * 100;
    }


    public List<AssetBook> getAssetBook() throws IOException {
        List<AssetBook> userAssets = new ArrayList<>();
        List<Instrument> instruments = instrumentRepo.findAll().stream()
                .filter(instrument -> "stock".equalsIgnoreCase(instrument.getType()))
                .collect(Collectors.toList());
        System.out.println(instruments);

        for (Instrument i : instruments) {
            // Instrument instrument = order.getInstrument();
            String tickerSymbol = i.getTickerSymbol();
            String companyName = i.getCompanyName();
            double currentMarketPrice = getPricePerShareFromFile(tickerSymbol);
            int volume = i.getVolume();
            double averageBuyPrice = i.getAverageBuyPrice();
            double pnl = calculatePNL(i, currentMarketPrice);
            double pnlPercentage = calculatePNLPercentage(pnl, averageBuyPrice, volume);

            AssetBook assetBook = new AssetBook();
            assetBook.setTickerSymbol(tickerSymbol);
            assetBook.setVolume(volume);
            assetBook.setCompanyName(companyName);
            assetBook.setAverageBuyPrice(averageBuyPrice);
            assetBook.setCurrentMarketPrice(currentMarketPrice);

            assetBook.setPnl(pnl);
            assetBook.setPnlPercentage(pnlPercentage);

            userAssets.add(assetBook);

            // Print to console
            // System.out.println("Order Summary: " + orderSummary);
        }

        return userAssets;


    }


    public List<CashflowBook> getCashFlowBook() throws IOException {
        List<CashflowBook> cashflowBooks = new ArrayList<>();

        List<Instrument> instruments = instrumentRepo.findAll().stream()
                .filter(instrument -> "stock".equalsIgnoreCase(instrument.getType()))
                .collect(Collectors.toList());
        List<Cashflow> cashflows = cashflowRepo.findAll();
        for(Cashflow c:cashflows){
            Instrument instrument = c.getInstrument();
            String tickerSymbol = c.getTickerSymbol();
            String companyName = instrument.getCompanyName();
            double currentMarketPrice = getPricePerShareFromFile(tickerSymbol);
            int volume = c.getVolume();
            Date transactiondate=c.getTransactionDate();
            double averageBuyPrice = instrument.getAverageBuyPrice();
            double pnl =c.getPnl();

            CashflowBook cashflowBook = new CashflowBook();
            cashflowBook.setTickerSymbol(tickerSymbol);
            cashflowBook.setVolume(volume);
            cashflowBook.setCompanyName(companyName);
            cashflowBook.setAverageBuyPrice(averageBuyPrice);
            cashflowBook.setCurrentMarketPrice(currentMarketPrice);

            cashflowBook.setPnl(pnl);
            cashflowBook.setTransactionDate(transactiondate);

            cashflowBooks.add(cashflowBook);


        }
        return cashflowBooks;

    }

}
