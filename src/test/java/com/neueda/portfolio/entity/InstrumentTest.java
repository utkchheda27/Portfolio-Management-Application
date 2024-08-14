package com.neueda.portfolio.entity;


import com.neueda.portfolio.Entity.Instrument;
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

        // Act
        instrument.setTickerSymbol(tickerSymbol);
        instrument.setCompanyName(companyName);
        instrument.setAverageBuyPrice(averageBuyPrice);
        instrument.setVolume(volume);

        // Assert
        assertEquals(tickerSymbol, instrument.getTickerSymbol());
        assertEquals(companyName, instrument.getCompanyName());
        assertEquals(averageBuyPrice, instrument.getAverageBuyPrice());
        assertEquals(volume, instrument.getVolume());
    }

    @Test
    public void testConstructor() {
        // Arrange
        String tickerSymbol = "AAPL";
        String companyName = "Apple Inc.";
        double averageBuyPrice = 150.0;
        int volume = 100;

        // Act
        Instrument instrument = new Instrument(tickerSymbol, companyName, averageBuyPrice, volume);

        // Assert
        assertEquals(tickerSymbol, instrument.getTickerSymbol());
        assertEquals(companyName, instrument.getCompanyName());
        assertEquals(averageBuyPrice, instrument.getAverageBuyPrice());
        assertEquals(volume, instrument.getVolume());
    }

    @Test
    public void testToString() {
        // Arrange
        String tickerSymbol = "AAPL";
        String companyName = "Apple Inc.";
        double averageBuyPrice = 150.0;
        int volume = 100;
        Instrument instrument = new Instrument(tickerSymbol, companyName, averageBuyPrice, volume);

        // Act
        String expectedToString = "Instrument{tickerSymbol='AAPL', companyName='Apple Inc.', averageBuyPrice=150.0, volume=100}";
        String actualToString = instrument.toString();

        // Assert
        assertEquals(expectedToString, actualToString);
    }
}

