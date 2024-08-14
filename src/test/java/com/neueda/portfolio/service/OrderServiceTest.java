package com.neueda.portfolio.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neueda.portfolio.entity.*;
import com.neueda.portfolio.repo.CashflowRepo;
import com.neueda.portfolio.repo.InstrumentRepo;
import com.neueda.portfolio.repo.OrderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private InstrumentRepo instrumentRepo;

    @Mock
    private OrderRepo orderRepo;

    @Mock
    private CashflowRepo cashflowRepo;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCashFlow() {
        List<Cashflow> cashflowList = new ArrayList<>();
        cashflowList.add(new Cashflow());
        when(cashflowRepo.findAll()).thenReturn(cashflowList);

        List<Cashflow> result = orderService.getCashFlow();
        assertEquals(1, result.size());
    }

    @Test
    void testGetTradeBook() {
        List<Instrument> instrumentList = new ArrayList<>();
        instrumentList.add(new Instrument());
        when(instrumentRepo.findAll()).thenReturn(instrumentList);

        List<Instrument> result = orderService.gettradebook();
        assertEquals(1, result.size());
    }

    @Test
    void testGetTradeBookByTickerSymbol() {
        List<Instrument> instrumentList = new ArrayList<>();
        instrumentList.add(new Instrument());
        when(instrumentRepo.findBytickerSymbol(anyString())).thenReturn(instrumentList);

        List<Instrument> result = orderService.gettradebookBytickerSymbol("AAPL");
        assertEquals(1, result.size());
    }

    @Test
    void testGetOrders() {
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(new Orders());
        when(orderRepo.findAll()).thenReturn(ordersList);

        List<Orders> result = orderService.getOrders();
        assertEquals(1, result.size());
    }

    @Test
    void testGetOrdersByTickerSymbol() {
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(new Orders());
        when(orderRepo.findBytickerSymbol(anyString())).thenReturn(ordersList);

        List<Orders> result = orderService.getOrdersBytickerSymbol("AAPL");
        assertEquals(1, result.size());
    }

    @Test
    void testProcessOrder_Buy() throws IOException {
        Instrument instrument = new Instrument();
        instrument.setTickerSymbol("AAPL");
        instrument.setVolume(100);
        instrument.setAverageBuyPrice(150.0);

        when(instrumentRepo.findById("AAPL")).thenReturn(Optional.of(instrument));
        when(orderRepo.save(any(Orders.class))).thenReturn(new Orders());
        when(cashflowRepo.save(any(Cashflow.class))).thenReturn(new Cashflow());

        orderService.processOrder("AAPL", 10, "Buy", new Date());

        verify(instrumentRepo, times(1)).save(any(Instrument.class));
        verify(orderRepo, times(1)).save(any(Orders.class));
    }

    @Test
    void testProcessOrder_Sell() throws IOException {
        Instrument instrument = new Instrument();
        instrument.setTickerSymbol("AAPL");
        instrument.setVolume(100);
        instrument.setAverageBuyPrice(150.0);

        when(instrumentRepo.findById("AAPL")).thenReturn(Optional.of(instrument));
        when(orderRepo.save(any(Orders.class))).thenReturn(new Orders());
        when(cashflowRepo.save(any(Cashflow.class))).thenReturn(new Cashflow());

        orderService.processOrder("AAPL", 10, "Sell", new Date());

        verify(instrumentRepo, times(1)).save(any(Instrument.class));
        verify(orderRepo, times(1)).save(any(Orders.class));
        verify(cashflowRepo, times(1)).save(any(Cashflow.class));
    }

    @Test
    void testProcessOrder_InstrumentNotFound() {
        when(instrumentRepo.findById("AAPL")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderService.processOrder("AAPL", 10, "Sell", new Date());
        });

        assertEquals("Cannot sell an instrument that is not held", exception.getMessage());
    }

  /*  @Test
    void testGetOrderSummaries() throws IOException {
        Instrument instrument = new Instrument();
        instrument.setTickerSymbol("AAPL");
        instrument.setCompanyName("Apple");
        instrument.setVolume(100);
        instrument.setAverageBuyPrice(150.0);

        Orders order = new Orders();
        order.setInstrument(instrument);
        order.setTickerSymbol("AAPL");

        when(orderRepo.findAll()).thenReturn(Collections.singletonList(order));
        when(instrumentRepo.findById("AAPL")).thenReturn(Optional.of(instrument));
        when(orderService.getOrderSummaries()).thenCallRealMethod();

        List<OrderSummary> result = orderService.getOrderSummaries();
        assertEquals(1, result.size());
    }*/
    @Test
    void testGetOrderSummaries() throws IOException {
        // Arrange: Mock dependencies and interactions
        Instrument instrument = new Instrument();
        instrument.setTickerSymbol("AAPL");
        instrument.setVolume(100);
        instrument.setAverageBuyPrice(150.0);

        Orders order = new Orders();
        order.setInstrument(instrument);
        order.setTickerSymbol("AAPL");
        order.setTransactionDate(new Date());

        when(orderRepo.findAll()).thenReturn(Collections.singletonList(order));

        // Mocking the private method
        OrderService spyOrderService = spy(orderService);
        doReturn(150.0).when(spyOrderService).getPricePerShareFromFile(anyString());

        // Act: Call the method under test
        List<OrderSummary> result = spyOrderService.getOrderSummaries();

        // Assert: Verify the result
        assertEquals(1, result.size());
        OrderSummary summary = result.get(0);
        assertEquals(null, summary.getInstrumentName());
        assertEquals(150.0, summary.getCurrentMarketPrice());
        assertEquals(100, summary.getVolume());
        assertEquals(150.0, summary.getBoughtPrice());
        // Additional assertions as needed
    }


    @Test
    void testGetAssetBook() throws IOException {
        Instrument instrument = new Instrument();
        instrument.setTickerSymbol("AAPL");
        instrument.setVolume(100);
        instrument.setAverageBuyPrice(150.0);

        when(instrumentRepo.findAll()).thenReturn(Collections.singletonList(instrument));

        List<AssetBook> result = orderService.getAssetBook();
        assertEquals(1, result.size());
    }

    @Test
    void testGetCashFlowBook() throws IOException {
        Instrument instrument = new Instrument();
        instrument.setTickerSymbol("AAPL");
        instrument.setVolume(100);
        instrument.setAverageBuyPrice(150.0);

        Cashflow cashflow = new Cashflow();
        cashflow.setTickerSymbol("AAPL");
        cashflow.setInstrument(instrument);

        when(cashflowRepo.findAll()).thenReturn(Collections.singletonList(cashflow));

        List<CashflowBook> result = orderService.getCashFlowBook();
        assertEquals(1, result.size());
    }

    // Mock private method with PowerMockito
    @Test
    void testGetPricePerShareFromFile() throws Exception {
        ObjectMapper objectMapper = mock(ObjectMapper.class);
        Map<String, Object> companyData = new HashMap<>();
        companyData.put("pricePerShare", 196.45);

        Map<String, Map<String, Object>> data = new HashMap<>();
        data.put("AAPL", companyData);

        when(objectMapper.readValue(any(InputStream.class), eq(Map.class))).thenReturn(data);

        double price = orderService.getPricePerShareFromFile("AAPL");
        assertEquals(196.45, price);
    }
}
