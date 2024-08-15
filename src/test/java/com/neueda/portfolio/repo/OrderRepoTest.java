package com.neueda.portfolio.repo;
import java.util.stream.Collectors;

import com.neueda.portfolio.repo.InstrumentRepo;
import com.neueda.portfolio.repo.OrderRepo;
import com.neueda.portfolio.entity.Instrument;
import com.neueda.portfolio.entity.Orders;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Use this if you don't want to replace your actual DB
class OrderRepoTest {

    @Autowired
    private OrderRepo orderRepo;
    
    @Autowired
    private InstrumentRepo instrumentRepo; // Add this for the instrument repository

   /* @Test
    void testFindByTickerSymbol() {
    	
    	// Setup the Instrument data
        Instrument instrument1 = new Instrument();
        instrument1.setTickerSymbol("AAPL");
        instrument1.setVolume(10);
        instrumentRepo.save(instrument1);
        // Given
        Orders order1 = new Orders();
        order1.setTickerSymbol("AAPL");
        order1.setVolume(10);
        order1.setAction("buy");
        orderRepo.save(order1);

        
       /* 
        Orders order2 = new Orders();
        order2.setTickerSymbol("AAPL");
        order2.setVolume(50);
        order2.setAction("sell");
        orderRepo.save(order2);

        Orders order3 = new Orders();
        order3.setTickerSymbol("GOOGL");
        order3.setVolume(200);
        order3.setAction("buy");
        orderRepo.save(order3);*/

        // When
     /*   List<Orders> aaplOrders = orderRepo.findBytickerSymbol("AAPL");

        // Then
        assertThat(aaplOrders).hasSize(2);
        assertThat(aaplOrders).extracting(Orders::getVolume).containsExactlyInAnyOrder(100, 50);
    }*/
    
    @Test
    void testFindByTickerSymbol() {
        // Setup the Instrument data
        Instrument instrument = new Instrument();
        instrument.setTickerSymbol("AAPL");
        instrumentRepo.save(instrument);

        // Setup the Order data with expected values
        Orders order1 = new Orders();
        order1.setTickerSymbol("AAPL");
        order1.setVolume(100);  // Ensure this matches your expectation
        orderRepo.save(order1);

        Orders order2 = new Orders();
        order2.setTickerSymbol("AAPL");
        order2.setVolume(50);   // Ensure this matches your expectation
        orderRepo.save(order2);
        

        // Test the method
        List<Orders> orders = orderRepo.findBytickerSymbol("AAPL");
        
        // Extract volumes
        List<Integer> actualVolumes = orders.stream()
            .map(Orders::getVolume)
            .collect(Collectors.toList());

        // Assertions
        assertThat(actualVolumes).containsExactlyInAnyOrder(100, 50);
    }


    @Test
    void testSaveOrder() {
    	
    	// Setup the Instrument data
        Instrument instrument = new Instrument();
        instrument.setTickerSymbol("MSFT");
        instrument.setVolume(150);
        instrumentRepo.save(instrument);
        // Given
        Orders order = new Orders();
        order.setTickerSymbol("MSFT");
        order.setVolume(150);
        order.setAction("buy");

        // When
        Orders savedOrder = orderRepo.save(order);

        // Then
        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getId()).isNotNull(); // Ensure ID is generated
        assertThat(savedOrder.getTickerSymbol()).isEqualTo("MSFT");
        assertThat(savedOrder.getVolume()).isEqualTo(150);
    }

    @Test
    void testFindAllOrders() {
        // Given
        Orders order1 = new Orders();
        order1.setTickerSymbol("TSLA");
        order1.setVolume(15);
        order1.setAction("buy");
        orderRepo.save(order1);

        Orders order2 = new Orders();
        order2.setTickerSymbol("TSLA");
        order2.setVolume(5);
        order2.setAction("sell");
        orderRepo.save(order2);

        // When
        List<Orders> allOrders = orderRepo.findAll();

        // Then
        assertThat(allOrders).hasSize(2);
    }
}
