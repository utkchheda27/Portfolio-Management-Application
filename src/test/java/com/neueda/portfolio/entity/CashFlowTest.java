package com.neueda.portfolio.entity;


import org.junit.jupiter.api.Test;

import com.neueda.portfolio.entity.Cashflow;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CashFlowTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        Cashflow cashflow = new Cashflow();
        Long id = 1L;
        String tickerSymbol = "AAPL";
        double pnl = 1000.0;
        Date transactionDate = new Date();
        int volume = 10;

        // Act
        cashflow.setId(id);
        cashflow.setTickerSymbol(tickerSymbol);
        cashflow.setPnl(pnl);
        cashflow.setTransactionDate(transactionDate);
        cashflow.setVolume(volume);

        // Assert
        assertEquals(id, cashflow.getId());
        assertEquals(tickerSymbol, cashflow.getTickerSymbol());
        assertEquals(pnl, cashflow.getPnl());
        assertEquals(transactionDate, cashflow.getTransactionDate());
        assertEquals(volume, cashflow.getVolume());
    }

    @Test
    public void testToString() {
        // Arrange
        Cashflow cashflow = new Cashflow();
        cashflow.setId(1L);
        cashflow.setTickerSymbol("AAPL");
        cashflow.setPnl(1000.0);
        cashflow.setTransactionDate(new Date());
        cashflow.setVolume(10);

        // Act
        String result = cashflow.toString();

        // Assert
        String expected = "Cashflow{" +
                "id=1" +
                ", tickerSymbol='AAPL'" +
                ", pnl=1000.0" +
                ", volume=10" +
                ", transactionDate=" + cashflow.getTransactionDate() +
                '}';
        assertEquals(expected, result);
    }
}
