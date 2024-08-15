package com.neueda.portfolio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neueda.portfolio.entity.Instrument;
import com.neueda.portfolio.service.InstrumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@WebMvcTest(InstrumentController.class)
public class InstrumentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InstrumentService instrumentService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAllInstruments() throws Exception {
        Instrument instrument1 = new Instrument();
        instrument1.setTickerSymbol("AAPL");
        instrument1.setCompanyName("Apple Inc.");
        
        Instrument instrument2 = new Instrument();
        instrument2.setTickerSymbol("GOOGL");
        instrument2.setCompanyName("Alphabet Inc.");
        
        when(instrumentService.getAllInstruments()).thenReturn(Arrays.asList(instrument1, instrument2));

        mockMvc.perform(MockMvcRequestBuilders.get("/instruments")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].tickerSymbol").value("AAPL"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].tickerSymbol").value("GOOGL"));

        verify(instrumentService, times(1)).getAllInstruments();
    }

    @Test
    public void testGetInstrumentByTickerSymbol() throws Exception {
        Instrument instrument = new Instrument();
        instrument.setTickerSymbol("AAPL");
        instrument.setCompanyName("Apple Inc.");
        
        when(instrumentService.getInstrumentByTickerSymbol("AAPL")).thenReturn(Optional.of(instrument));

        mockMvc.perform(MockMvcRequestBuilders.get("/instruments/AAPL")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tickerSymbol").value("AAPL"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyName").value("Apple Inc."));

        verify(instrumentService, times(1)).getInstrumentByTickerSymbol("AAPL");
    }

    @Test
    public void testGetInstrumentByTickerSymbolNotFound() throws Exception {
        when(instrumentService.getInstrumentByTickerSymbol("AAPL")).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/instruments/AAPL")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(instrumentService, times(1)).getInstrumentByTickerSymbol("AAPL");
    }

    @Test
    public void testCreateInstrument() throws Exception {
        Instrument instrument = new Instrument();
        instrument.setTickerSymbol("AAPL");
        instrument.setCompanyName("Apple Inc.");
        
        when(instrumentService.saveInstrument(any(Instrument.class))).thenReturn(instrument);

        mockMvc.perform(MockMvcRequestBuilders.post("/instruments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(instrument)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tickerSymbol").value("AAPL"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyName").value("Apple Inc."));

        verify(instrumentService, times(1)).saveInstrument(any(Instrument.class));
    }

    @Test
    public void testDeleteInstrument() throws Exception {
        doNothing().when(instrumentService).deleteInstrument("AAPL");

        mockMvc.perform(MockMvcRequestBuilders.delete("/instruments/AAPL")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(instrumentService, times(1)).deleteInstrument("AAPL");
    }
}
