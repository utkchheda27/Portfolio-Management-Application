package com.neueda.portfolio.entity;
import com.neueda.portfolio.entity.stock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class stockTest {

    @Test
    public void testStockEntity() {
        // Create a new stock object
        stock stock = new stock();
        
        // Set values
        stock.setId(1L);
        stock.setTickerSymbol("AAPL");
        stock.setCompanyName("Apple Inc.");
        stock.setStockPrice(150.25);
        stock.setWeek52High(180.00);
        stock.setWeek52Low(120.50);
        stock.setAverageVolume(5000000L);
        stock.setIndustry("Technology");
        stock.setMarketExchange("NASDAQ");
        
        // Assert that getters return the correct values
        assertEquals(1L, stock.getId());
        assertEquals("AAPL", stock.getTickerSymbol());
        assertEquals("Apple Inc.", stock.getCompanyName());
        assertEquals(150.25, stock.getStockPrice());
        assertEquals(180.00, stock.getWeek52High());
        assertEquals(120.50, stock.getWeek52Low());
        assertEquals(5000000L, stock.getAverageVolume());
        assertEquals("Technology", stock.getIndustry());
        assertEquals("NASDAQ", stock.getMarketExchange());
    }
}

