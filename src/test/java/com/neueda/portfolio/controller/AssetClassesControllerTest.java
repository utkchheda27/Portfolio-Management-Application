package com.neueda.portfolio.controller;


import com.neueda.portfolio.Controller.AssetClassesController;
import com.neueda.portfolio.Service.bondService;
import com.neueda.portfolio.Service.stockService;
import com.neueda.portfolio.Entity.bond;
import com.neueda.portfolio.Entity.stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AssetClassesControllerTest {

    @InjectMocks
    private AssetClassesController assetClassesController;

    @Mock
    private stockService stockService;

    @Mock
    private bondService bondService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAllBondSuccess() {
        List<bond> bonds = List.of(new bond(), new bond());
        when(bondService.getAllBond()).thenReturn(bonds);

        ResponseEntity<Object> response = assetClassesController.allBond();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", ((Map<?, ?>) response.getBody()).get("status"));
    }

    @Test
    void testAllBondFailure() {
        when(bondService.getAllBond()).thenThrow(new RuntimeException("Error"));

        ResponseEntity<Object> response = assetClassesController.allBond();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("FAILURE", ((Map<?, ?>) response.getBody()).get("status"));
    }

    @Test
    void testAllStockSuccess() {
        List<stock> stocks = List.of(new stock(), new stock());
        when(stockService.getAllStock()).thenReturn(stocks);

        ResponseEntity<Object> response = assetClassesController.allStock();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", ((Map<?, ?>) response.getBody()).get("status"));
    }

    @Test
    void testAllStockFailure() {
        when(stockService.getAllStock()).thenThrow(new RuntimeException("Error"));

        ResponseEntity<Object> response = assetClassesController.allStock();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("FAILURE", ((Map<?, ?>) response.getBody()).get("status"));
    }

    @Test
    void testStockByNameSuccess() {
        List<stock> stocks = List.of(new stock(), new stock());
        when(stockService.findByTickerSymbol("AAPL")).thenReturn(stocks);

        ResponseEntity<Object> response = assetClassesController.stockByName("AAPL");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", ((Map<?, ?>) response.getBody()).get("status"));
    }

    @Test
    void testStockByNameFailure() {
        when(stockService.findByTickerSymbol("AAPL")).thenReturn(Collections.emptyList());

        ResponseEntity<Object> response = assetClassesController.stockByName("AAPL");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("FAILURE", ((Map<?, ?>) response.getBody()).get("status"));
        assertEquals("No stocks found for the given ticker symbol", ((Map<?, ?>) response.getBody()).get("error"));
    }

    @Test
    void testBondByNameSuccess() {
        List<bond> bonds = List.of(new bond(), new bond());
        when(bondService.findByTickerSymbol("US10Y")).thenReturn(bonds);

        ResponseEntity<Object> response = assetClassesController.bondByName("US10Y");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", ((Map<?, ?>) response.getBody()).get("status"));
    }

    @Test
    void testBondByNameFailure() {
        when(bondService.findByTickerSymbol("US10Y")).thenReturn(Collections.emptyList());

        ResponseEntity<Object> response = assetClassesController.bondByName("US10Y");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("FAILURE", ((Map<?, ?>) response.getBody()).get("status"));
        assertEquals("No bonds found for the given ticker symbol", ((Map<?, ?>) response.getBody()).get("error"));
    }
}
