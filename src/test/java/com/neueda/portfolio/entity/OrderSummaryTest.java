package com.neueda.portfolio.entity;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderSummaryTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        OrderSummary orderSummary = new OrderSummary();
        String instrumentName = "Apple Inc.";
        Date dateOfPurchase = new Date();
        int volume = 100;
        double boughtPrice = 150.0;
        double currentMarketPrice = 175.0;
        double currentMarketValue = volume * currentMarketPrice;
        double pnl = currentMarketValue - (volume * boughtPrice);
        double pnlPercentage = (pnl / (volume * boughtPrice)) * 100;

        // Act
        orderSummary.setInstrumentName(instrumentName);
        orderSummary.setDateOfPurchase(dateOfPurchase);
        orderSummary.setVolume(volume);
        orderSummary.setBoughtPrice(boughtPrice);
        orderSummary.setCurrentMarketPrice(currentMarketPrice);
        orderSummary.setCurrentMarketValue(currentMarketValue);
        orderSummary.setPnl(pnl);
        orderSummary.setPnlPercentage(pnlPercentage);

        // Assert
        assertEquals(instrumentName, orderSummary.getInstrumentName());
        assertEquals(dateOfPurchase, orderSummary.getDateOfPurchase());
        assertEquals(volume, orderSummary.getVolume());
        assertEquals(boughtPrice, orderSummary.getBoughtPrice());
        assertEquals(currentMarketPrice, orderSummary.getCurrentMarketPrice());
        assertEquals(currentMarketValue, orderSummary.getCurrentMarketValue());
        assertEquals(pnl, orderSummary.getPnl());
        assertEquals(pnlPercentage, orderSummary.getPnlPercentage());
    }

    @Test
    public void testToString() {
        // Arrange
        String instrumentName = "Apple Inc.";
        Date dateOfPurchase = new Date();
        int volume = 100;
        double boughtPrice = 150.0;
        double currentMarketPrice = 175.0;
        double currentMarketValue = volume * currentMarketPrice;
        double pnl = currentMarketValue - (volume * boughtPrice);
        double pnlPercentage = (pnl / (volume * boughtPrice)) * 100;
        OrderSummary orderSummary = new OrderSummary();
        orderSummary.setInstrumentName(instrumentName);
        orderSummary.setDateOfPurchase(dateOfPurchase);
        orderSummary.setVolume(volume);
        orderSummary.setBoughtPrice(boughtPrice);
        orderSummary.setCurrentMarketPrice(currentMarketPrice);
        orderSummary.setCurrentMarketValue(currentMarketValue);
        orderSummary.setPnl(pnl);
        orderSummary.setPnlPercentage(pnlPercentage);

        // Act
        String expectedToString = "OrderSummary{" +
                "instrumentName='" + instrumentName + '\'' +
                ", dateOfPurchase=" + dateOfPurchase +
                ", volume=" + volume +
                ", boughtPrice=" + boughtPrice +
                ", currentMarketPrice=" + currentMarketPrice +
                ", currentMarketValue=" + currentMarketValue +
                ", pnl=" + pnl +
                ", pnlPercentage=" + pnlPercentage +
                '}';
        String actualToString = orderSummary.toString();

        // Assert
        assertEquals(expectedToString, actualToString);
    }
}
