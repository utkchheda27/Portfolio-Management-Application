package com.neueda.portfolio.Controller;


import com.neueda.portfolio.Entity.Instrument;
import com.neueda.portfolio.Entity.Orders;
import com.neueda.portfolio.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

   /* @GetMapping
    public List<Orders> getOrders() {
        return orderService.getAllOrders();
    }
    @GetMapping("/{id}")
    public Instrument getInstrument(@PathVariable String id){
        return orderService.getInstrumentByTicker(id);

    }*/

    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(
            @RequestParam String tickerSymbol,
            @RequestParam int volume,
            @RequestParam String action) {

        try {
            if (tickerSymbol == null || tickerSymbol.isEmpty()) {
                return ResponseEntity.badRequest().body("Ticker symbol cannot be null or empty.");
            }
            if (volume <= 0) {
                return ResponseEntity.badRequest().body("Volume must be greater than zero.");
            }
            if (!action.equalsIgnoreCase("buy") && !action.equalsIgnoreCase("sell")) {
                return ResponseEntity.badRequest().body("Action must be either 'buy' or 'sell'.");
            }

            Date transactionDate=new Date();
            orderService.processOrder(tickerSymbol, volume, action, transactionDate);


            return ResponseEntity.ok("Order processed successfully.");

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the order: " + e.getMessage());
        }
    }


}
