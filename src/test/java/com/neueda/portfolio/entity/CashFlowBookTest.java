package com.neueda.portfolio.entity;


import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashFlowBookTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        CashflowBook cashflowBook = new CashflowBook();
        String tickerSymbol = "AAPL";
        String companyName = "Apple Inc.";
        int volume = 100;
        double currentMarketPrice = 175.0;
        double averageBuyPrice = 150.0;
        double pnl = 2500.0;
        Date transactionDate = new Date();

        // Act
        cashflowBook.setTickerSymbol(tickerSymbol);
        cashflowBook.setCompanyName(companyName);
        cashflowBook.setVolume(volume);
        cashflowBook.setCurrentMarketPrice(currentMarketPrice);
        cashflowBook.setAverageBuyPrice(averageBuyPrice);
        cashflowBook.setPnl(pnl);
        cashflowBook.setTransactionDate(transactionDate);

        // Assert
        assertEquals(tickerSymbol, cashflowBook.getTickerSymbol());
        assertEquals(companyName, cashflowBook.getCompanyName());
        assertEquals(volume, cashflowBook.getVolume());
        assertEquals(currentMarketPrice, cashflowBook.getCurrentMarketPrice());
        assertEquals(averageBuyPrice, cashflowBook.getAverageBuyPrice());
        assertEquals(pnl, cashflowBook.getPnl());
        assertEquals(transactionDate, cashflowBook.getTransactionDate());
    }

    @Test
    public void testConstructor() {
        // Arrange
        String tickerSymbol = "AAPL";
        String companyName = "Apple Inc.";
        int volume = 100;
        double currentMarketPrice = 175.0;
        double averageBuyPrice = 150.0;
        double pnl = 2500.0;
        Date transactionDate = new Date();

        // Act
        CashflowBook cashflowBook = new CashflowBook(tickerSymbol, companyName, volume, currentMarketPrice, averageBuyPrice, pnl, transactionDate);

        // Assert
        assertEquals(tickerSymbol, cashflowBook.getTickerSymbol());
        assertEquals(companyName, cashflowBook.getCompanyName());
        assertEquals(volume, cashflowBook.getVolume());
        assertEquals(currentMarketPrice, cashflowBook.getCurrentMarketPrice());
        assertEquals(averageBuyPrice, cashflowBook.getAverageBuyPrice());
        assertEquals(pnl, cashflowBook.getPnl());
        assertEquals(transactionDate, cashflowBook.getTransactionDate());
    }
}
