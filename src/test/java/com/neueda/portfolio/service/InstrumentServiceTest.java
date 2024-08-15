package com.neueda.portfolio.service;

import com.neueda.portfolio.entity.Instrument;
import com.neueda.portfolio.repo.InstrumentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class InstrumentServiceTest {

    @Mock
    private InstrumentRepo instrumentRepo;

    @InjectMocks
    private InstrumentService instrumentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllInstruments() {
        Instrument instrument1 = new Instrument();
        instrument1.setTickerSymbol("AAPL");
        instrument1.setCompanyName("Apple Inc.");
        
        Instrument instrument2 = new Instrument();
        instrument2.setTickerSymbol("GOOGL");
        instrument2.setCompanyName("Alphabet Inc.");
        
        when(instrumentRepo.findAll()).thenReturn(Arrays.asList(instrument1, instrument2));

        List<Instrument> instruments = instrumentService.getAllInstruments();
        
        assertNotNull(instruments);
        assertEquals(2, instruments.size());
        assertEquals("AAPL", instruments.get(0).getTickerSymbol());
        assertEquals("GOOGL", instruments.get(1).getTickerSymbol());

        verify(instrumentRepo, times(1)).findAll();
    }

    @Test
    public void testGetInstrumentByTickerSymbolFound() {
        Instrument instrument = new Instrument();
        instrument.setTickerSymbol("AAPL");
        instrument.setCompanyName("Apple Inc.");
        
        when(instrumentRepo.findById("AAPL")).thenReturn(Optional.of(instrument));

        Optional<Instrument> result = instrumentService.getInstrumentByTickerSymbol("AAPL");

        assertTrue(result.isPresent());
        assertEquals("AAPL", result.get().getTickerSymbol());
        assertEquals("Apple Inc.", result.get().getCompanyName());

        verify(instrumentRepo, times(1)).findById("AAPL");
    }

    @Test
    public void testGetInstrumentByTickerSymbolNotFound() {
        when(instrumentRepo.findById("AAPL")).thenReturn(Optional.empty());

        Optional<Instrument> result = instrumentService.getInstrumentByTickerSymbol("AAPL");

        assertFalse(result.isPresent());

        verify(instrumentRepo, times(1)).findById("AAPL");
    }

    @Test
    public void testGetInstrumentByType() {
        Instrument instrument1 = new Instrument();
        instrument1.setTickerSymbol("AAPL");
        instrument1.setCompanyName("Apple Inc.");
        
        Instrument instrument2 = new Instrument();
        instrument2.setTickerSymbol("GOOGL");
        instrument2.setCompanyName("Alphabet Inc.");
        
        when(instrumentRepo.findBytype("Tech")).thenReturn(Arrays.asList(instrument1, instrument2));

        List<Instrument> instruments = instrumentService.getInstrumentBytype("Tech");

        assertNotNull(instruments);
        assertEquals(2, instruments.size());
        assertEquals("AAPL", instruments.get(0).getTickerSymbol());
        assertEquals("GOOGL", instruments.get(1).getTickerSymbol());

        verify(instrumentRepo, times(1)).findBytype("Tech");
    }

    @Test
    public void testSaveInstrument() {
        Instrument instrument = new Instrument();
        instrument.setTickerSymbol("AAPL");
        instrument.setCompanyName("Apple Inc.");
        
        when(instrumentRepo.save(any(Instrument.class))).thenReturn(instrument);

        Instrument result = instrumentService.saveInstrument(instrument);

        assertNotNull(result);
        assertEquals("AAPL", result.getTickerSymbol());
        assertEquals("Apple Inc.", result.getCompanyName());

        verify(instrumentRepo, times(1)).save(any(Instrument.class));
    }

    @Test
    public void testDeleteInstrument() {
        doNothing().when(instrumentRepo).deleteById("AAPL");

        instrumentService.deleteInstrument("AAPL");

        verify(instrumentRepo, times(1)).deleteById("AAPL");
    }
}
