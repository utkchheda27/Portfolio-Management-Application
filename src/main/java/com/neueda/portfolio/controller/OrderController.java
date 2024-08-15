package com.neueda.portfolio.controller;

import com.neueda.portfolio.service.OrderService;
import com.neueda.portfolio.entity.*;
import com.neueda.portfolio.service.OrderService;
import com.neueda.portfolio.exception.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})

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
   /* @GetMapping("/cashflowbook")
    public List<Cashflow> cashFlowBook() {
        return orderService.getCashFlow();
    }

    @GetMapping("/orderbook")
    public List<Orders> orderBook(){
        return orderService.getOrders();
    }*/
    @GetMapping("/tradebook")
    public List<Instrument> gettradebook(@RequestParam(required = false) String tickerSymbol){
        if(tickerSymbol==null){
            return orderService.gettradebook();
        }
        return orderService.gettradebookBytickerSymbol(tickerSymbol);

    }
    @GetMapping("/orderbook")
    public List<Orders> getorderbook(@RequestParam(required = false) String tickerSymbol)
    {
        if(tickerSymbol==null)
            return orderService.getOrders();
        return orderService.getOrdersBytickerSymbol(tickerSymbol);
    }

    @GetMapping("/cashflowbook")
    public ResponseEntity<List<CashflowBook>> getcashFlowBook() {
        try {
            List<CashflowBook> cashflowbooks = orderService.getCashFlowBook();
            return ResponseEntity.ok(cashflowbooks);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // to fetch the asset class list
    @GetMapping("/orderSummaries")
    public ResponseEntity<List<OrderSummary>> getOrderSummaries() {
        try {
            List<OrderSummary> orderSummaries = orderService.getOrderSummaries();
            return ResponseEntity.ok(orderSummaries);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    @GetMapping("/assetBook")
    public ResponseEntity<List<AssetBook>> getAssetBook() {
        try {
            List<AssetBook> userAssets = orderService.getAssetBook();
            return ResponseEntity.ok(userAssets);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }


    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(
            @RequestParam String tickerSymbol,
            @RequestParam String action,
            @RequestParam int volume
    ) {

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
