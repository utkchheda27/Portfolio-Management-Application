package com.neueda.portfolio.entity;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssetBookTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        AssetBook assetBook = new AssetBook();
        assetBook.setTickerSymbol("AAPL");
        assetBook.setVolume(100);
        assetBook.setCompanyName("Apple Inc.");
        assetBook.setAverageBuyPrice(150.0);
        assetBook.setCurrentMarketPrice(175.0);
        assetBook.setPnl(2500.0);
        assetBook.setPnlPercentage(16.67);

        // Act & Assert
        assertEquals("AAPL", assetBook.getTickerSymbol());
        assertEquals(100, assetBook.getVolume());
        assertEquals("Apple Inc.", assetBook.getCompanyName());
        assertEquals(150.0, assetBook.getAverageBuyPrice());
        assertEquals(175.0, assetBook.getCurrentMarketPrice());
        assertEquals(2500.0, assetBook.getPnl());
        assertEquals(16.67, assetBook.getPnlPercentage());
    }

    @Test
    public void testToString() {
        // Arrange
        AssetBook assetBook = new AssetBook();
        assetBook.setTickerSymbol("AAPL");
        assetBook.setVolume(100);
        assetBook.setCompanyName("Apple Inc.");
        assetBook.setAverageBuyPrice(150.0);
        assetBook.setCurrentMarketPrice(175.0);
        assetBook.setPnl(2500.0);
        assetBook.setPnlPercentage(16.67);

        // Act
        String result = assetBook.toString();

        // Assert
        String expected = "OrderSummary{tickerSymbol='AAPL', companyName='Apple Inc.', volume=100, averageBuyPrice=150.0, currentMarketPrice=175.0, pnl=2500.0, pnlPercentage=16.67}";
        assertEquals(expected, result);
    }
}
