package com.neueda.portfolio.service;

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
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
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

 /* @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }*/
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderService = spy(orderService); // Spy on the already injected service
    }
    
    @Test
    void testGetCashFlow() {
        // Arrange
        List<Cashflow> expectedCashflows = Arrays.asList(new Cashflow(), new Cashflow());
        when(cashflowRepo.findAll()).thenReturn(expectedCashflows);

        // Act
        List<Cashflow> actualCashflows = orderService.getCashFlow();

        // Assert
        assertEquals(expectedCashflows, actualCashflows);
        verify(cashflowRepo, times(1)).findAll();
    }

    @Test
    void testGetTradebook() {
        // Arrange
        Instrument instrument1 = new Instrument();
        instrument1.setType("stock");
        Instrument instrument2 = new Instrument();
        instrument2.setType("bond");
        Instrument instrument3 = new Instrument();
        instrument3.setType("stock");

        List<Instrument> allInstruments = Arrays.asList(instrument1, instrument2, instrument3);
        when(instrumentRepo.findAll()).thenReturn(allInstruments);

        // Act
        List<Instrument> tradebook = orderService.gettradebook();

        // Assert
        assertEquals(2, tradebook.size());
        assertTrue(tradebook.contains(instrument1));
        assertTrue(tradebook.contains(instrument3));
        assertFalse(tradebook.contains(instrument2));
    }

    @Test
    void testGetTradebookByTickerSymbol() {
        // Arrange
        String tickerSymbol = "AAPL";
        List<Instrument> expectedInstruments = Arrays.asList(new Instrument(), new Instrument());
        when(instrumentRepo.findBytickerSymbol(tickerSymbol)).thenReturn(expectedInstruments);

        // Act
        List<Instrument> actualInstruments = orderService.gettradebookBytickerSymbol(tickerSymbol);

        // Assert
        assertEquals(expectedInstruments, actualInstruments);
        verify(instrumentRepo, times(1)).findBytickerSymbol(tickerSymbol);
    }

    @Test
    void testGetOrders() {
        // Arrange
        List<Orders> expectedOrders = Arrays.asList(new Orders(), new Orders());
        when(orderRepo.findAll()).thenReturn(expectedOrders);

        // Act
        List<Orders> actualOrders = orderService.getOrders();

        // Assert
        assertEquals(expectedOrders, actualOrders);
        verify(orderRepo, times(1)).findAll();
    }

    @Test
    void testGetOrdersByTickerSymbol() {
        // Arrange
        String tickerSymbol = "AAPL";
        List<Orders> expectedOrders = Arrays.asList(new Orders(), new Orders());
        when(orderRepo.findBytickerSymbol(tickerSymbol)).thenReturn(expectedOrders);

        // Act
        List<Orders> actualOrders = orderService.getOrdersBytickerSymbol(tickerSymbol);

        // Assert
        assertEquals(expectedOrders, actualOrders);
        verify(orderRepo, times(1)).findBytickerSymbol(tickerSymbol);
    }

    @Test
    void testProcessOrder_Buy() throws IOException {
        // Arrange
        String tickerSymbol = "AAPL";
        int volume = 100;
        String action = "Buy";
        Date transactionDate = new Date();

        Instrument instrument = new Instrument(tickerSymbol, "Apple Inc.", 150.0, 50, action);
        when(instrumentRepo.findById(tickerSymbol)).thenReturn(Optional.of(instrument));
        when(instrumentRepo.save(any(Instrument.class))).thenReturn(instrument);
        when(orderRepo.save(any(Orders.class))).thenReturn(new Orders());

        // Act
        orderService.processOrder(tickerSymbol, volume, action, transactionDate);

        // Assert
        verify(instrumentRepo, times(1)).save(any(Instrument.class));
        verify(orderRepo, times(1)).save(any(Orders.class));
    }

    @Test
    void testProcessOrder_Sell() throws IOException {
        // Arrange
        String tickerSymbol = "AAPL";
        int volume = 50;
        String action = "Sell";
        Date transactionDate = new Date();

        Instrument instrument = new Instrument(tickerSymbol, "Apple Inc.", 150.0, 100, action);
        when(instrumentRepo.findById(tickerSymbol)).thenReturn(Optional.of(instrument));
        when(instrumentRepo.save(any(Instrument.class))).thenReturn(instrument);
        when(orderRepo.save(any(Orders.class))).thenReturn(new Orders());
        when(cashflowRepo.save(any(Cashflow.class))).thenReturn(new Cashflow());

        // Act
        orderService.processOrder(tickerSymbol, volume, action, transactionDate);

        // Assert
        verify(instrumentRepo, times(1)).save(any(Instrument.class));
        verify(orderRepo, times(1)).save(any(Orders.class));
        verify(cashflowRepo, times(1)).save(any(Cashflow.class));
    }

    @Test
    void testProcessOrder_InvalidSell() {
        // Arrange
        String tickerSymbol = "AAPL";
        int volume = 100;
        String action = "Sell";

        when(instrumentRepo.findById(tickerSymbol)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            orderService.processOrder(tickerSymbol, volume, action, null);
        });

        assertEquals("Cannot sell an instrument that is not held", thrown.getMessage());
        verify(instrumentRepo, times(1)).findById(tickerSymbol);
    }

    @Test
    void testGetOrderSummaries() throws IOException {
        // Arrange
        List<Orders> mockOrders = new ArrayList<>();
        Orders mockOrder = mock(Orders.class);
        Instrument mockInstrument = mock(Instrument.class);

        // Stubbing the mock order and instrument methods
        when(mockOrder.getInstrument()).thenReturn(mockInstrument);
        when(mockOrder.getTickerSymbol()).thenReturn("AAPL"); // Ensuring a non-null tickerSymbol
        when(mockOrder.getTransactionDate()).thenReturn(new Date());

        when(mockInstrument.getCompanyName()).thenReturn("Apple Inc.");
        when(mockInstrument.getVolume()).thenReturn(10);
        when(mockInstrument.getAverageBuyPrice()).thenReturn(150.0);

        mockOrders.add(mockOrder);

        when(orderRepo.findAll()).thenReturn(mockOrders);
        when(orderService.getPricePerShareFromFile("AAPL")).thenReturn(200.0); // Stubbing the price retrieval

        // Act
        List<OrderSummary> orderSummaries = orderService.getOrderSummaries();

        // Assert
        assertEquals(1, orderSummaries.size());
        assertEquals("Apple Inc.", orderSummaries.get(0).getInstrumentName());
        assertEquals(10, orderSummaries.get(0).getVolume());
        assertEquals(150.0, orderSummaries.get(0).getBoughtPrice());
        assertEquals(200.0, orderSummaries.get(0).getCurrentMarketPrice());
        // Additional assertions as needed
    }


    @Test
    void testGetAssetBook() throws IOException {
        // Arrange
        List<Instrument> mockInstruments = new ArrayList<>();
        Instrument mockInstrument = mock(Instrument.class);

        // Stubbing the mock instrument methods
        when(mockInstrument.getTickerSymbol()).thenReturn("AAPL");
        when(mockInstrument.getCompanyName()).thenReturn("Apple Inc.");
        when(mockInstrument.getVolume()).thenReturn(10);
        when(mockInstrument.getAverageBuyPrice()).thenReturn(150.0);
        when(mockInstrument.getType()).thenReturn("stock");

        mockInstruments.add(mockInstrument);
        when(instrumentRepo.findAll()).thenReturn(mockInstruments);
        when(orderService.getPricePerShareFromFile("AAPL")).thenReturn(200.0);

        // Act
        List<AssetBook> assetBooks = orderService.getAssetBook();

        // Assert
        assertEquals(1, assetBooks.size());
        assertEquals("AAPL", assetBooks.get(0).getTickerSymbol());
        assertEquals(10, assetBooks.get(0).getVolume());
        assertEquals("Apple Inc.", assetBooks.get(0).getCompanyName());
        assertEquals(150.0, assetBooks.get(0).getAverageBuyPrice());
        assertEquals(200.0, assetBooks.get(0).getCurrentMarketPrice());
        // Additional assertions as needed
    }


    @Test
    void testGetCashFlowBook() throws IOException {
        // Arrange
        List<Cashflow> mockCashflows = new ArrayList<>();
        Cashflow mockCashflow = mock(Cashflow.class);
        Instrument mockInstrument = mock(Instrument.class);

        // Stubbing the mock cashflow and instrument methods
        when(mockCashflow.getInstrument()).thenReturn(mockInstrument);
        when(mockCashflow.getTickerSymbol()).thenReturn("AAPL");
        when(mockCashflow.getPnl()).thenReturn(500.0);
        when(mockCashflow.getVolume()).thenReturn(10);
        when(mockCashflow.getTransactionDate()).thenReturn(new Date());

        when(mockInstrument.getCompanyName()).thenReturn("Apple Inc.");
        when(mockInstrument.getAverageBuyPrice()).thenReturn(150.0);

        mockCashflows.add(mockCashflow);
        when(cashflowRepo.findAll()).thenReturn(mockCashflows);
        when(instrumentRepo.findBytickerSymbol("AAPL")).thenReturn(List.of(mockInstrument));
        when(orderService.getPricePerShareFromFile("AAPL")).thenReturn(200.0);

        // Act
        List<CashflowBook> cashflowBooks = orderService.getCashFlowBook();

        // Assert
        assertEquals(1, cashflowBooks.size());
        assertEquals("AAPL", cashflowBooks.get(0).getTickerSymbol());
        assertEquals(10, cashflowBooks.get(0).getVolume());
        assertEquals("Apple Inc.", cashflowBooks.get(0).getCompanyName());
        assertEquals(150.0, cashflowBooks.get(0).getAverageBuyPrice());
        assertEquals(200.0, cashflowBooks.get(0).getCurrentMarketPrice());
        assertEquals(500.0, cashflowBooks.get(0).getPnl());
        // Additional assertions as needed
    }
}