package com.neueda.portfolio.controller;

import com.neueda.portfolio.entity.bond;
import com.neueda.portfolio.entity.stock;
import com.neueda.portfolio.service.bondService;
import com.neueda.portfolio.service.stockService;
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
    private stockService stockservice;

    @Mock
    private bondService bondservice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAllBondSuccess() {
        List<bond> bonds = List.of(new bond(), new bond());
        when(bondservice.getAllBond()).thenReturn(bonds);

        ResponseEntity<Object> response = assetClassesController.allBond();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", ((Map<?, ?>) response.getBody()).get("status"));
    }

    @Test
    void testAllBondFailure() {
        when(bondservice.getAllBond()).thenThrow(new RuntimeException("Error"));

        ResponseEntity<Object> response = assetClassesController.allBond();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("FAILURE", ((Map<?, ?>) response.getBody()).get("status"));
    }

    @Test
    void testAllStockSuccess() {
        List<stock> stocks = List.of(new stock(), new stock());
        when(stockservice.getAllStock()).thenReturn(stocks);

        ResponseEntity<Object> response = assetClassesController.allStock();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", ((Map<?, ?>) response.getBody()).get("status"));
    }

    @Test
    void testAllStockFailure() {
        when(stockservice.getAllStock()).thenThrow(new RuntimeException("Error"));

        ResponseEntity<Object> response = assetClassesController.allStock();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("FAILURE", ((Map<?, ?>) response.getBody()).get("status"));
    }

    @Test
    void testStockByNameSuccess() {
        List<stock> stocks = List.of(new stock(), new stock());
        when(stockservice.findByTickerSymbol("AAPL")).thenReturn(stocks);

        ResponseEntity<Object> response = assetClassesController.stockByName("AAPL");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", ((Map<?, ?>) response.getBody()).get("status"));
    }

    @Test
    void testStockByNameFailure() {
        when(stockservice.findByTickerSymbol("AAPL")).thenReturn(Collections.emptyList());

        ResponseEntity<Object> response = assetClassesController.stockByName("AAPL");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("FAILURE", ((Map<?, ?>) response.getBody()).get("status"));
        assertEquals("No stocks found for the given ticker symbol", ((Map<?, ?>) response.getBody()).get("error"));
    }

    @Test
    void testBondByNameSuccess() {
        List<bond> bonds = List.of(new bond(), new bond());
        when(bondservice.findByTickerSymbol("US10Y")).thenReturn(bonds);

        ResponseEntity<Object> response = assetClassesController.bondByName("US10Y");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", ((Map<?, ?>) response.getBody()).get("status"));
    }

    @Test
    void testBondByNameFailure() {
        when(bondservice.findByTickerSymbol("US10Y")).thenReturn(Collections.emptyList());

        ResponseEntity<Object> response = assetClassesController.bondByName("US10Y");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("FAILURE", ((Map<?, ?>) response.getBody()).get("status"));
        assertEquals("No bonds found for the given ticker symbol", ((Map<?, ?>) response.getBody()).get("error"));
    }
}
