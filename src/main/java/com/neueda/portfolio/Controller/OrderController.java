/*package com.neueda.portfolio.Controller;

import com.neueda.portfolio.Entity.*;
import com.neueda.portfolio.Service.OrderService;
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


}*/
package com.neueda.portfolio.Controller;

import com.neueda.portfolio.Entity.*;
import com.neueda.portfolio.Service.OrderService;
import com.neueda.portfolio.exception.ResponseUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @GetMapping("/tradebook")
    public List<Instrument> gettradebook(@RequestParam(required = false) String tickerSymbol) {
        logger.info("Entered gettradebook method with tickerSymbol: {}", tickerSymbol);
        List<Instrument> instruments;
        if (tickerSymbol == null) {
            instruments = orderService.gettradebook();
            logger.info("Fetching all tradebook entries");
        } else {
            instruments = orderService.gettradebookBytickerSymbol(tickerSymbol);
            logger.info("Fetching tradebook entries for tickerSymbol: {}", tickerSymbol);
        }
        logger.debug("Tradebook fetched with {} entries", instruments.size());
        return instruments;
    }

    @GetMapping("/orderbook")
    public List<Orders> getorderbook(@RequestParam(required = false) String tickerSymbol) {
        logger.info("Entered getorderbook method with tickerSymbol: {}", tickerSymbol);
        List<Orders> orders;
        if (tickerSymbol == null) {
            orders = orderService.getOrders();
            logger.info("Fetching all orderbook entries");
        } else {
            orders = orderService.getOrdersBytickerSymbol(tickerSymbol);
            logger.info("Fetching orderbook entries for tickerSymbol: {}", tickerSymbol);
        }
        logger.debug("Orderbook fetched with {} entries", orders.size());
        return orders;
    }

    @GetMapping("/cashflowbook")
    public ResponseEntity<List<CashflowBook>> getcashFlowBook() {
        logger.info("Entered getcashFlowBook method");
        try {
            List<CashflowBook> cashflowbooks = orderService.getCashFlowBook();
            logger.info("Successfully fetched cash flow book with {} entries", cashflowbooks.size());
            return ResponseEntity.ok(cashflowbooks);
        } catch (IOException e) {
            logger.error("Error fetching cash flow book", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/orderSummaries")
    public ResponseEntity<List<OrderSummary>> getOrderSummaries() {
        logger.info("Entered getOrderSummaries method");
        try {
            List<OrderSummary> orderSummaries = orderService.getOrderSummaries();
            logger.info("Successfully fetched order summaries with {} entries", orderSummaries.size());
            return ResponseEntity.ok(orderSummaries);
        } catch (IOException e) {
            logger.error("Error fetching order summaries", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/assetBook")
    public ResponseEntity<List<AssetBook>> getAssetBook() {
        logger.info("Entered getAssetBook method");
        try {
            List<AssetBook> userAssets = orderService.getAssetBook();
            logger.info("Successfully fetched asset book with {} entries", userAssets.size());
            return ResponseEntity.ok(userAssets);
        } catch (IOException e) {
            logger.error("Error fetching asset book", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(
            @RequestParam String tickerSymbol,
            @RequestParam String action,
            @RequestParam int volume
    ) {
        logger.info("Entered createOrder method with tickerSymbol: {}, action: {}, volume: {}", tickerSymbol, action, volume);
        try {
            if (tickerSymbol == null || tickerSymbol.isEmpty()) {
                logger.warn("Invalid ticker symbol: {}", tickerSymbol);
                return ResponseEntity.badRequest().body("Ticker symbol cannot be null or empty.");
            }
            if (volume <= 0) {
                logger.warn("Invalid volume: {}", volume);
                return ResponseEntity.badRequest().body("Volume must be greater than zero.");
            }
            if (!action.equalsIgnoreCase("buy") && !action.equalsIgnoreCase("sell")) {
                logger.warn("Invalid action: {}", action);
                return ResponseEntity.badRequest().body("Action must be either 'buy' or 'sell'.");
            }

            Date transactionDate = new Date();
            logger.info("Processing order with tickerSymbol: {}, action: {}, volume: {}, transactionDate: {}", tickerSymbol, action, volume, transactionDate);
            orderService.processOrder(tickerSymbol, volume, action, transactionDate);

            logger.info("Order processed successfully");
            return ResponseEntity.ok("Order processed successfully.");

        } catch (Exception e) {
            logger.error("Error processing order with tickerSymbol: {}, action: {}, volume: {}", tickerSymbol, action, volume, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the order: " + e.getMessage());
        }
    }
}
