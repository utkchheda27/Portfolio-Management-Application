package com.neueda.portfolio.entity;
import com.neueda.portfolio.Entity.bond;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class bondTest {

    @Test
    public void testBondEntity() {
        // Create a new bond object
        bond bond = new bond();
        
        // Set values
        bond.setId(1L);
        bond.setTickerSymbol("TSLA");
        bond.setIssuer("Tesla");
        bond.setCouponRate(5.5);
        bond.setFaceValue(1000);
        bond.setMaturityDate("2028-12-31");
        bond.setCreditRating("AA");
        bond.setBondPrice(1050.75);
        
        // Assert that getters return the correct values
        assertEquals(1L, bond.getId());
        assertEquals("TSLA", bond.getTickerSymbol());
        assertEquals("Tesla", bond.getIssuer());
        assertEquals(5.5, bond.getCouponRate());
        assertEquals(1000, bond.getFaceValue());
        assertEquals("2028-12-31", bond.getMaturityDate());
        assertEquals("AA", bond.getCreditRating());
        assertEquals(1050.75, bond.getBondPrice());
    }
}
