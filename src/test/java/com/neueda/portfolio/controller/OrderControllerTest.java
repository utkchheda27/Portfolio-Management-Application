package com.neueda.portfolio.controller;

import com.neueda.portfolio.controller.OrderController;
import com.neueda.portfolio.service.OrderService;
import com.neueda.portfolio.entity.AssetBook;
import com.neueda.portfolio.entity.Cashflow;
import com.neueda.portfolio.entity.CashflowBook;
import com.neueda.portfolio.entity.Instrument;
import com.neueda.portfolio.entity.OrderSummary;
import com.neueda.portfolio.entity.Orders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.mockito.Mockito.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void testGetTradeBookWithoutTickerSymbol() throws Exception {
        List<Instrument> instrumentList = new ArrayList<>();
        instrumentList.add(new Instrument());
        when(orderService.gettradebook()).thenReturn(instrumentList);

        mockMvc.perform(get("/api/tradebook")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void testGetTradeBookWithTickerSymbol() throws Exception {
        List<Instrument> instrumentList = new ArrayList<>();
        instrumentList.add(new Instrument());
        when(orderService.gettradebookBytickerSymbol(anyString())).thenReturn(instrumentList);

        mockMvc.perform(get("/api/tradebook")
                .param("tickerSymbol", "AAPL")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void testGetOrderBookWithoutTickerSymbol() throws Exception {
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(new Orders());
        when(orderService.getOrders()).thenReturn(ordersList);

        mockMvc.perform(get("/api/orderbook")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void testGetOrderBookWithTickerSymbol() throws Exception {
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(new Orders());
        when(orderService.getOrdersBytickerSymbol(anyString())).thenReturn(ordersList);

        mockMvc.perform(get("/api/orderbook")
                .param("tickerSymbol", "AAPL")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    
    @Test
    void testGetCashflowBook() throws Exception {
        // Correct type for the list
        List<CashflowBook> cashflowList = new ArrayList<>();
        cashflowList.add(new CashflowBook());

        // Mocking the service method call to return the correct list
        when(orderService.getCashFlowBook()).thenReturn(cashflowList);

        // Perform the GET request and validate the response
        mockMvc.perform(get("/api/cashflowbook")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }


    @Test
    void testGetOrderSummaries() throws Exception {
        List<OrderSummary> orderSummaryList = new ArrayList<>();
        orderSummaryList.add(new OrderSummary());
        when(orderService.getOrderSummaries()).thenReturn(orderSummaryList);

        mockMvc.perform(get("/api/orderSummaries")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void testGetAssetBook() throws Exception {
        List<AssetBook> assetBookList = new ArrayList<>();
        assetBookList.add(new AssetBook());
        when(orderService.getAssetBook()).thenReturn(assetBookList);

        mockMvc.perform(get("/api/assetBook")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void testCreateOrder_Success() throws Exception {
        mockMvc.perform(post("/api/createOrder")
                .param("tickerSymbol", "AAPL")
                .param("action", "buy")
                .param("volume", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Order processed successfully."));
    }

    @Test
    void testCreateOrder_InvalidTickerSymbol() throws Exception {
        mockMvc.perform(post("/api/createOrder")
                .param("tickerSymbol", "")
                .param("action", "buy")
                .param("volume", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Ticker symbol cannot be null or empty."));
    }

    @Test
    void testCreateOrder_InvalidVolume() throws Exception {
        mockMvc.perform(post("/api/createOrder")
                .param("tickerSymbol", "AAPL")
                .param("action", "buy")
                .param("volume", "0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Volume must be greater than zero."));
    }

    @Test
    void testCreateOrder_InvalidAction() throws Exception {
        mockMvc.perform(post("/api/createOrder")
                .param("tickerSymbol", "AAPL")
                .param("action", "hold")
                .param("volume", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Action must be either 'buy' or 'sell'."));
    }

   /* @Test
    void testCreateOrder_InternalServerError() throws Exception {
        when(orderService.processOrder(anyString(), anyInt(), anyString(), any(Date.class)))
                .thenThrow(new RuntimeException("Test Exception"));

        mockMvc.perform(post("/api/createOrder")
                .param("tickerSymbol", "AAPL")
                .param("action", "buy")
                .param("volume", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("An error occurred while processing the order: Test Exception"));
    }*/
    
 
}


/*
import com.neueda.portfolio.entity.Cashflow;
import com.neueda.portfolio.entity.Instrument;
import com.neueda.portfolio.entity.OrderSummary;
import com.neueda.portfolio.entity.Orders;
import com.neueda.portfolio.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class OrderControllerTest {
	 @Mock
	    private OrderService orderService;

	    @InjectMocks
	    private OrderController orderController;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.initMocks(this);
	    }

	    // Test for getOrderSummaries
	    @Test
	    void testGetOrderSummaries_Success() throws IOException {
	        List<OrderSummary> mockOrderSummaries = Arrays.asList(new OrderSummary(), new OrderSummary());
	        when(orderService.getOrderSummaries()).thenReturn(mockOrderSummaries);

	        ResponseEntity<List<OrderSummary>> response = orderController.getOrderSummaries();

	        assertEquals(200, response.getStatusCodeValue());
	        assertNotNull(response.getBody());
	        assertEquals(2, response.getBody().size());
	    }

	    @Test
	    void testGetOrderSummaries_Exception() throws IOException {
	        when(orderService.getOrderSummaries()).thenThrow(new IOException("Test Exception"));

	        ResponseEntity<List<OrderSummary>> response = orderController.getOrderSummaries();

	        assertEquals(500, response.getStatusCodeValue());
	        assertEquals(null, response.getBody());
	    }

	    // Test for createOrder
	    @Test
	    void testCreateOrder_Success() throws IOException {
	        ResponseEntity<String> response = orderController.createOrder("AAPL", "buy", 100);

	        assertEquals(200, response.getStatusCodeValue());
	        assertEquals("Order processed successfully.", response.getBody());
	        verify(orderService, times(1)).processOrder(eq("AAPL"), eq(100), eq("buy"), any(Date.class));
	    }

	    @Test
	    void testCreateOrder_InvalidTickerSymbol() {
	        ResponseEntity<String> response = orderController.createOrder("", "buy", 100);

	        assertEquals(400, response.getStatusCodeValue());
	        assertEquals("Ticker symbol cannot be null or empty.", response.getBody());
	    }

	    @Test
	    void testCreateOrder_InvalidVolume() {
	        ResponseEntity<String> response = orderController.createOrder("AAPL", "buy", 0);

	        assertEquals(400, response.getStatusCodeValue());
	        assertEquals("Volume must be greater than zero.", response.getBody());
	    }

	    @Test
	    void testCreateOrder_InvalidAction() {
	        ResponseEntity<String> response = orderController.createOrder("AAPL", "hold", 100);

	        assertEquals(400, response.getStatusCodeValue());
	        assertEquals("Action must be either 'buy' or 'sell'.", response.getBody());
	    }

	    @Test
	    void testCreateOrder_Exception() throws IOException {
	        doThrow(new RuntimeException("Test Exception")).when(orderService).processOrder(anyString(), anyInt(), anyString(), any(Date.class));

	        ResponseEntity<String> response = orderController.createOrder("AAPL", "buy", 100);

	        assertEquals(500, response.getStatusCodeValue());
	        assertEquals("An error occurred while processing the order: Test Exception", response.getBody());
	    }

	    // Test for getorderbook
	    @Test
	    void testGetOrderbook_WithTickerSymbol() {
	        List<Orders> mockOrders = Arrays.asList(new Orders(), new Orders());
	        when(orderService.getOrdersBytickerSymbol("AAPL")).thenReturn(mockOrders);

	        List<Orders> response = orderController.getorderbook("AAPL");

	        assertNotNull(response);
	        assertEquals(2, response.size());
	    }

	    @Test
	    void testGetOrderbook_WithoutTickerSymbol() {
	        List<Orders> mockOrders = Arrays.asList(new Orders(), new Orders());
	        when(orderService.getOrders()).thenReturn(mockOrders);

	        List<Orders> response = orderController.getorderbook(null);

	        assertNotNull(response);
	        assertEquals(2, response.size());
	    }

	    // Test for gettradebook
	    @Test
	    void testGetTradebook_WithTickerSymbol() {
	        List<Instrument> mockInstruments = Arrays.asList(new Instrument(), new Instrument());
	        when(orderService.gettradebookBytickerSymbol("AAPL")).thenReturn(mockInstruments);

	        List<Instrument> response = orderController.gettradebook("AAPL");

	        assertNotNull(response);
	        assertEquals(2, response.size());
	    }

	    @Test
	    void testGetTradebook_WithoutTickerSymbol() {
	        List<Instrument> mockInstruments = Arrays.asList(new Instrument(), new Instrument());
	        when(orderService.gettradebook()).thenReturn(mockInstruments);

	        List<Instrument> response = orderController.gettradebook(null);

	        assertNotNull(response);
	        assertEquals(2, response.size());
	    }

	    // Test for getcashflowbook
	    @Test
	    void testGetCashflowbook_WithTickerSymbol() {
	        List<Cashflow> mockCashflows = Arrays.asList(new Cashflow(), new Cashflow());
	        when(orderService.getCashflowbytickerSymbol("AAPL")).thenReturn(mockCashflows);

	        List<Cashflow> response = orderController.getcashflowbook("AAPL");

	        assertNotNull(response);
	        assertEquals(2, response.size());
	    }

	    @Test
	    void testGetCashflowbook_WithoutTickerSymbol() {
	        List<Cashflow> mockCashflows = Arrays.asList(new Cashflow(), new Cashflow());
	        when(orderService.getCashFlow()).thenReturn(mockCashflows);

	        List<Cashflow> response = orderController.getcashflowbook(null);

	        assertNotNull(response);
	        assertEquals(2, response.size());
	    }

}*/
