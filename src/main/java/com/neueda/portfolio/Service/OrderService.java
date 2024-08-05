package com.neueda.portfolio.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neueda.portfolio.Entity.Cashflow;
import com.neueda.portfolio.Entity.Instrument;
import com.neueda.portfolio.Entity.Orders;
import com.neueda.portfolio.Repo.CashflowRepo;
import com.neueda.portfolio.Repo.InstrumentRepo;
import com.neueda.portfolio.Repo.OrderRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private InstrumentRepo instrumentRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CashflowRepo cashflowRepo;

    public List<Cashflow> getCashFlow(){
        return cashflowRepo.findAll();

    }
    public List<Orders> getOrders(){
        return orderRepo.findAll();
    }
    public List<Orders> getOrdersBytickerSymbol(String tickerSymbol){
        return orderRepo.findBytickerSymbol(tickerSymbol);

    }
    public List<Cashflow> getCashflowbytickerSymbol(String tickerSymbol){
        return cashflowRepo.findBytickerSymbol(tickerSymbol);
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public void processOrder(String tickerSymbol, int volume, String action, Date transactionDate) throws IOException {

        if (transactionDate == null) {
            transactionDate = new Date();
        }

        // Retrieve the instrument from the repository
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
                double newAveragePrice = ((instrument.getAverageBuyPrice() * instrument.getVolume()) + (volume * pricePerShare)) / totalVolume;
                instrument.setAverageBuyPrice(newAveragePrice);
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
            cashflowRepo.save(cashflow);
        }
    }

    private double getPricePerShareFromFile(String tickerSymbol) throws IOException {
        Map<String, Map<String, Object>> data = readPriceDataFromClassPath();
        Map<String, Object> companyData = data.get(tickerSymbol);
        if (companyData != null && companyData.containsKey("pricePerShare")) {
            return ((Number) companyData.get("pricePerShare")).doubleValue();
        }
        throw new RuntimeException("Price data not found for ticker symbol: " + tickerSymbol);
    }

    private String getCompanyNameFromFile(String tickerSymbol) throws IOException {
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

}
