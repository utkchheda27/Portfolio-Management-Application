package com.neueda.portfolio.service;


import com.neueda.portfolio.Entity.bond;
import com.neueda.portfolio.Repo.bondRepo;
import com.neueda.portfolio.Service.bondService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class bondServiceTest {

    @InjectMocks
    private bondService bondService;

    @Mock
    private bondRepo bondRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBond() {
        // Given
        bond bond1 = new bond();
        bond1.setTickerSymbol("BOND1");
        bond bond2 = new bond();
        bond2.setTickerSymbol("BOND2");
        List<bond> bonds = Arrays.asList(bond1, bond2);

        // When
        when(bondRepo.findAll()).thenReturn(bonds);

        // Then
        List<bond> result = bondService.getAllBond();
        assertEquals(2, result.size());
        assertEquals("BOND1", result.get(0).getTickerSymbol());
        assertEquals("BOND2", result.get(1).getTickerSymbol());
    }

    @Test
    void testFindByTickerSymbol() {
        // Given
        bond bond1 = new bond();
        bond1.setTickerSymbol("BOND1");
        List<bond> bonds = Arrays.asList(bond1);

        // When
        when(bondRepo.findBytickerSymbol("BOND1")).thenReturn(bonds);

        // Then
        List<bond> result = bondService.findByTickerSymbol("BOND1");
        assertEquals(1, result.size());
        assertEquals("BOND1", result.get(0).getTickerSymbol());
    }
}

