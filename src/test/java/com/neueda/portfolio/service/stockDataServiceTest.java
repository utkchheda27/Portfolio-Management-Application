package com.neueda.portfolio.service;

import com.neueda.portfolio.Service.StockDataService;
import com.neueda.portfolio.Entity.StockData;
import com.neueda.portfolio.Repo.StockDataRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class stockDataServiceTest {

    @Mock
    private StockDataRepo stockDataRepo;

    @InjectMocks
    private StockDataService stockDataService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStockData() {
        // Arrange
        StockData stockData1 = new StockData(1L, "AAPL", "2024-08-14T10:00:00Z", 175.0, 180.0, 170.0, 178.0, 500000L);
        StockData stockData2 = new StockData(2L, "GOOGL", "2024-08-15T10:00:00Z", 2800.0, 2900.0, 2700.0, 2850.0, 300000L);
        List<StockData> stockDataList = Arrays.asList(stockData1, stockData2);

        when(stockDataRepo.findAll()).thenReturn(stockDataList);

        // Act
        List<StockData> result = stockDataService.getStockData();

        // Assert
        assertEquals(2, result.size());
        assertEquals("AAPL", result.get(0).getTickerSymbol());
        assertEquals("GOOGL", result.get(1).getTickerSymbol());
        verify(stockDataRepo, times(1)).findAll();
    }
}
