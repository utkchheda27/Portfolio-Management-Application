package com.neueda.portfolio.entity;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InstrumentTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        Instrument instrument = new Instrument();
        String tickerSymbol = "AAPL";
        String companyName = "Apple Inc.";
        double averageBuyPrice = 150.0;
        int volume = 100;
        String type = "Apple Inc.";

        // Act
        instrument.setTickerSymbol(tickerSymbol);
        instrument.setCompanyName(companyName);
        instrument.setAverageBuyPrice(averageBuyPrice);
        instrument.setVolume(volume);
        instrument.setType(companyName);

        // Assert
        assertEquals(tickerSymbol, instrument.getTickerSymbol());
        assertEquals(companyName, instrument.getCompanyName());
        assertEquals(averageBuyPrice, instrument.getAverageBuyPrice());
        assertEquals(volume, instrument.getVolume());
        assertEquals(type, instrument.getType());
    }

    @Test
    public void testConstructor() {
        // Arrange
        String tickerSymbol = "AAPL";
        String companyName = "Apple Inc.";
        double averageBuyPrice = 150.0;
        int volume = 100;
        String type = "Apple Inc.";

        // Act
        Instrument instrument = new Instrument(tickerSymbol, companyName, averageBuyPrice, volume, type);

        // Assert
        assertEquals(tickerSymbol, instrument.getTickerSymbol());
        assertEquals(companyName, instrument.getCompanyName());
        assertEquals(averageBuyPrice, instrument.getAverageBuyPrice());
        assertEquals(volume, instrument.getVolume());
        assertEquals(type, instrument.getType());
    }

    @Test
    public void testToString() {
        // Arrange
        String tickerSymbol = "AAPL";
        String companyName = "Apple Inc.";
        double averageBuyPrice = 150.0;
        int volume = 100;
        String type = "Apple Inc.";
        Instrument instrument = new Instrument(tickerSymbol, companyName, averageBuyPrice, volume, companyName);

        // Act
        String expectedToString = "Instrument{tickerSymbol='AAPL', companyName='Apple Inc.', averageBuyPrice=150.0, volume=100, type=Apple Inc.}";
        String actualToString = instrument.toString();

        // Assert
        assertEquals(expectedToString, actualToString);
    }
}

