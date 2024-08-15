package com.neueda.portfolio.service;

import com.neueda.portfolio.repo.stockRepo;
import com.neueda.portfolio.entity.stock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class stockServiceTest {

    @Mock
    private stockRepo stockrepo;

    @InjectMocks
    private stockService stockservice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStock() {
        // Arrange
        stock stock1 = new stock();
        stock stock2 = new stock();
        List<stock> expectedStocks = Arrays.asList(stock1, stock2);

        when(stockrepo.findAll()).thenReturn(expectedStocks);

        // Act
        List<stock> actualStocks = stockservice.getAllStock();

        // Assert
        assertEquals(expectedStocks, actualStocks);
        verify(stockrepo, times(1)).findAll();
    }

    @Test
    void testFindByTickerSymbol() {
        // Arrange
        String tickerSymbol = "AAPL";
        stock stock1 = new stock();
        List<stock> expectedStocks = Arrays.asList(stock1);

        when(stockrepo.findBytickerSymbol(tickerSymbol)).thenReturn(expectedStocks);

        // Act
        List<stock> actualStocks = stockservice.findByTickerSymbol(tickerSymbol);

        // Assert
        assertEquals(expectedStocks, actualStocks);
        verify(stockrepo, times(1)).findBytickerSymbol(tickerSymbol);
    }
}

