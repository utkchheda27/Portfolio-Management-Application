package com.neueda.portfolio.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockDataTest {

    @Test
    public void testDefaultConstructor() {
        StockData stockData = new StockData();
        assertEquals(0, stockData.getId()); // default long value is 0
        assertEquals(null, stockData.getTickerSymbol());
        assertEquals(null, stockData.getTimestamp());
        assertEquals(null, stockData.getOpen());
        assertEquals(null, stockData.getHigh());
        assertEquals(null, stockData.getLow());
        assertEquals(null, stockData.getClose());
        assertEquals(null, stockData.getVolume());
    }

    @Test
    public void testParameterizedConstructor() {
        StockData stockData = new StockData(1L, "AAPL", "2024-08-14T10:00:00Z", 175.0, 180.0, 170.0, 178.0, 500000L);
        assertEquals(1L, stockData.getId());
        assertEquals("AAPL", stockData.getTickerSymbol());
        assertEquals("2024-08-14T10:00:00Z", stockData.getTimestamp());
        assertEquals(175.0, stockData.getOpen());
        assertEquals(180.0, stockData.getHigh());
        assertEquals(170.0, stockData.getLow());
        assertEquals(178.0, stockData.getClose());
        assertEquals(500000L, stockData.getVolume());
    }

    @Test
    public void testSettersAndGetters() {
        StockData stockData = new StockData();
        stockData.setId((int) 2L);
        stockData.setTickerSymbol("GOOGL");
        stockData.setTimestamp("2024-08-15T10:00:00Z");
        stockData.setOpen(2800.0);
        stockData.setHigh(2900.0);
        stockData.setLow(2700.0);
        stockData.setClose(2850.0);
        stockData.setVolume(300000L);

        assertEquals(2L, stockData.getId());
        assertEquals("GOOGL", stockData.getTickerSymbol());
        assertEquals("2024-08-15T10:00:00Z", stockData.getTimestamp());
        assertEquals(2800.0, stockData.getOpen());
        assertEquals(2900.0, stockData.getHigh());
        assertEquals(2700.0, stockData.getLow());
        assertEquals(2850.0, stockData.getClose());
        assertEquals(300000L, stockData.getVolume());
    }

    @Test
    public void testSettersWithNullValues() {
        StockData stockData = new StockData();
        stockData.setId((int) 3L);
        stockData.setTickerSymbol(null);
        stockData.setTimestamp(null);
        stockData.setOpen(null);
        stockData.setHigh(null);
        stockData.setLow(null);
        stockData.setClose(null);
        stockData.setVolume(null);

        assertEquals(3L, stockData.getId());
        assertEquals(null, stockData.getTickerSymbol());
        assertEquals(null, stockData.getTimestamp());
        assertEquals(null, stockData.getOpen());
        assertEquals(null, stockData.getHigh());
        assertEquals(null, stockData.getLow());
        assertEquals(null, stockData.getClose());
        assertEquals(null, stockData.getVolume());
    }
}
