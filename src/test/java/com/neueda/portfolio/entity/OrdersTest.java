package com.neueda.portfolio.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.neueda.portfolio.entity.Instrument;
import com.neueda.portfolio.entity.Orders;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrdersTest {

    private Orders order;

    @BeforeEach
    public void setUp() {
        order = new Orders();
    }

    @Test
    public void testGetId() {
        order.setId(1L);
        assertEquals(1L, order.getId());
    }

    @Test
    public void testGetTickerSymbol() {
        order.setTickerSymbol("AAPL");
        assertEquals("AAPL", order.getTickerSymbol());
    }

    @Test
    public void testGetVolume() {
        order.setVolume(100);
        assertEquals(100, order.getVolume());
    }

    @Test
    public void testGetAction() {
        order.setAction("BUY");
        assertEquals("BUY", order.getAction());
    }

    @Test
    public void testGetTransactionDate() {
        Date date = new Date();
        order.setTransactionDate(date);
        assertEquals(date, order.getTransactionDate());
    }

    @Test
    public void testGetPricePerShare() {
        order.setPricePerShare(150.50);
        assertEquals(150.50, order.getPricePerShare());
    }

    @Test
    public void testGetTotalMoney() {
        order.setTotalMoney(15050.00);
        assertEquals(15050.00, order.getTotalMoney());
    }

    @Test
    public void testGetInstrument() {
        Instrument instrument = new Instrument();
        order.setInstrument(instrument);
        assertEquals(instrument, order.getInstrument());
    }

    @Test
    public void testSetId() {
        order.setId(2L);
        assertEquals(2L, order.getId());
    }

    @Test
    public void testSetTickerSymbol() {
        order.setTickerSymbol("GOOGL");
        assertEquals("GOOGL", order.getTickerSymbol());
    }

    @Test
    public void testSetVolume() {
        order.setVolume(200);
        assertEquals(200, order.getVolume());
    }

    @Test
    public void testSetAction() {
        order.setAction("SELL");
        assertEquals("SELL", order.getAction());
    }

    @Test
    public void testSetTransactionDate() {
        Date date = new Date();
        order.setTransactionDate(date);
        assertEquals(date, order.getTransactionDate());
    }

    @Test
    public void testSetPricePerShare() {
        order.setPricePerShare(250.75);
        assertEquals(250.75, order.getPricePerShare());
    }

    @Test
    public void testSetTotalMoney() {
        order.setTotalMoney(50150.00);
        assertEquals(50150.00, order.getTotalMoney());
    }

    @Test
    public void testSetInstrument() {
        Instrument instrument = new Instrument();
        order.setInstrument(instrument);
        assertEquals(instrument, order.getInstrument());
    }

    @Test
    public void testToString() {
        order.setId(1L);
        order.setTickerSymbol("AAPL");
        order.setVolume(100);
        order.setAction("BUY");
        Date date = new Date();
        order.setTransactionDate(date);
        order.setPricePerShare(150.50);
        order.setTotalMoney(15050.00);

        String expected = "Order{" +
                "id=" + 1L +
                ", tickerSymbol='AAPL'" +
                ", volume=" + 100 +
                ", action='BUY'" +
                ", transactionDate=" + date +
                ", pricePerShare=" + 150.50 +
                ", totalMoney=" + 15050.00 +
                '}';
        assertEquals(expected, order.toString());
    }
}
