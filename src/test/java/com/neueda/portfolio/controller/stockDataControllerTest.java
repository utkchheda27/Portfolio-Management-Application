package com.neueda.portfolio.controller;

import com.neueda.portfolio.controller.StockDataController;
import com.neueda.portfolio.service.StockDataService;
import com.neueda.portfolio.entity.StockData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class stockDataControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StockDataService stockDataService;

    @InjectMocks
    private StockDataController stockDataController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(stockDataController).build();
    }

    @Test
    public void testGetAllStockData() throws Exception {
        // Arrange
        StockData stockData1 = new StockData(1L, "AAPL", "2024-08-14T10:00:00Z", 175.0, 180.0, 170.0, 178.0, 500000L);
        StockData stockData2 = new StockData(2L, "GOOGL", "2024-08-15T10:00:00Z", 2800.0, 2900.0, 2700.0, 2850.0, 300000L);
        List<StockData> stockDataList = Arrays.asList(stockData1, stockData2);

        when(stockDataService.getStockData()).thenReturn(stockDataList);

        // Act & Assert
        mockMvc.perform(get("/stockdata/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].tickerSymbol").value("AAPL"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].tickerSymbol").value("GOOGL"));
    }
}

