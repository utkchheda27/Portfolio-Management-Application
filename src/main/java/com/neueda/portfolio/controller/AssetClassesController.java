
/*package com.neueda.portfolio.Controller;

import com.neueda.portfolio.Entity.bond;
import com.neueda.portfolio.Entity.stock;
import com.neueda.portfolio.Service.bondService;
import com.neueda.portfolio.Service.stockService;
import com.neueda.portfolio.exception.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class AssetClassesController {

    public AssetClassesController() {
        System.out.println("Constructor called");
    }

    @Autowired
    private stockService stockservice;

    @Autowired
    private bondService bondservice;

    @Autowired
    private ResponseUtil responseutil;

    @GetMapping("/bond")
    public ResponseEntity<Object> allBond() {
        System.out.println("All bond");
        try {
            List<bond> bonds = bondservice.getAllBond();
            return ResponseEntity.ok(ResponseUtil.createResponse("SUCCESS", bonds, null));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ResponseUtil.createResponse("FAILURE", null, e.getMessage()));
        }
    }

    @GetMapping("/stock")
    public ResponseEntity<Object> allStock() {
        System.out.println("All stock");
        try {
            List<stock> stocks = stockservice.getAllStock();
            return ResponseEntity.ok(ResponseUtil.createResponse("SUCCESS", stocks, null));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ResponseUtil.createResponse("FAILURE", null, e.getMessage()));
        }
    }

    @GetMapping("/stock/company")
    public ResponseEntity<Object> stockByName(@RequestParam(required = false) String tickerSymbol) {
        try {
            List<stock> stocks = stockservice.findByTickerSymbol(tickerSymbol);
            if (stocks.isEmpty()) {
                return ResponseEntity.badRequest().body(ResponseUtil.createResponse("FAILURE", null, "No stocks found for the given ticker symbol"));
            }
            return ResponseEntity.ok(ResponseUtil.createResponse("SUCCESS", stocks, null));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ResponseUtil.createResponse("FAILURE", null, e.getMessage()));
        }
    }

    @GetMapping("/bond/company")
    public ResponseEntity<Object> bondByName(@RequestParam(required = false) String tickerSymbol) {
        try {
            List<bond> bonds = bondservice.findByTickerSymbol(tickerSymbol);
            if (bonds.isEmpty()) {
                return ResponseEntity.badRequest().body(ResponseUtil.createResponse("FAILURE", null, "No bonds found for the given ticker symbol"));
            }
            return ResponseEntity.ok(ResponseUtil.createResponse("SUCCESS", bonds, null));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ResponseUtil.createResponse("FAILURE", null, e.getMessage()));
        }
    }
}*/
/*package com.neueda.portfolio.Controller;

import com.neueda.portfolio.Entity.bond;
import com.neueda.portfolio.Entity.stock;
import com.neueda.portfolio.Service.bondService;
import com.neueda.portfolio.Service.stockService;
import com.neueda.portfolio.exception.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AssetClassesController {

    private static final Logger logger = LoggerFactory.getLogger(AssetClassesController.class);

    @Autowired
    private stockService stockservice;

    @Autowired
    private bondService bondservice;

    @Autowired
    private ResponseUtil responseutil;

    @GetMapping("/bond")
    public ResponseEntity<Object> allBond() {
        logger.info("Fetching all bonds");
        try {
            List<bond> bonds = bondservice.getAllBond();
            return ResponseEntity.ok(ResponseUtil.createResponse("SUCCESS", bonds, null));
        } catch (RuntimeException e) {
            logger.error("Error fetching bonds", e);
            return ResponseEntity.badRequest().body(ResponseUtil.createResponse("FAILURE", null, e.getMessage()));
        }
    }

    @GetMapping("/stock")
    public ResponseEntity<Object> allStock() {
        logger.info("Fetching all stocks");
        try {
            List<stock> stocks = stockservice.getAllStock();
            return ResponseEntity.ok(ResponseUtil.createResponse("SUCCESS", stocks, null));
        } catch (RuntimeException e) {
            logger.error("Error fetching stocks", e);
            return ResponseEntity.badRequest().body(ResponseUtil.createResponse("FAILURE", null, e.getMessage()));
        }
    }

    @GetMapping("/stock/company")
    public ResponseEntity<Object> stockByName(@RequestParam(required = false) String tickerSymbol) {
        logger.info("Fetching stocks for ticker symbol: {}", tickerSymbol);
        try {
            List<stock> stocks = stockservice.findByTickerSymbol(tickerSymbol);
            if (stocks.isEmpty()) {
                logger.warn("No stocks found for ticker symbol: {}", tickerSymbol);
                return ResponseEntity.badRequest().body(ResponseUtil.createResponse("FAILURE", null, "No stocks found for the given ticker symbol"));
            }
            return ResponseEntity.ok(ResponseUtil.createResponse("SUCCESS", stocks, null));
        } catch (RuntimeException e) {
            logger.error("Error fetching stocks by ticker symbol: {}", tickerSymbol, e);
            return ResponseEntity.badRequest().body(ResponseUtil.createResponse("FAILURE", null, e.getMessage()));
        }
    }

    @GetMapping("/bond/company")
    public ResponseEntity<Object> bondByName(@RequestParam(required = false) String tickerSymbol) {
        logger.info("Fetching bonds for ticker symbol: {}", tickerSymbol);
        try {
            List<bond> bonds = bondservice.findByTickerSymbol(tickerSymbol);
            if (bonds.isEmpty()) {
                logger.warn("No bonds found for ticker symbol: {}", tickerSymbol);
                return ResponseEntity.badRequest().body(ResponseUtil.createResponse("FAILURE", null, "No bonds found for the given ticker symbol"));
            }
            return ResponseEntity.ok(ResponseUtil.createResponse("SUCCESS", bonds, null));
        } catch (RuntimeException e) {
            logger.error("Error fetching bonds by ticker symbol: {}", tickerSymbol, e);
            return ResponseEntity.badRequest().body(ResponseUtil.createResponse("FAILURE", null, e.getMessage()));
        }
    }
}*/
package com.neueda.portfolio.Controller;

import com.neueda.portfolio.Entity.bond;
import com.neueda.portfolio.Entity.stock;
import com.neueda.portfolio.Service.bondService;
import com.neueda.portfolio.Service.stockService;
import com.neueda.portfolio.exception.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AssetClassesController {

    private static final Logger logger = LoggerFactory.getLogger(AssetClassesController.class);

    @Autowired
    private stockService stockservice;

    @Autowired
    private bondService bondservice;

    @Autowired
    private ResponseUtil responseutil;

    @GetMapping("/bond")
    public ResponseEntity<Object> allBond() {
        logger.debug("Received request to fetch all bonds");
        try {
            List<bond> bonds = bondservice.getAllBond();
            logger.info("Successfully fetched {} bonds", bonds.size());
            return ResponseEntity.ok(ResponseUtil.createResponse("SUCCESS", bonds, null));
        } catch (RuntimeException e) {
            logger.error("Error occurred while fetching all bonds", e);
            return ResponseEntity.badRequest().body(ResponseUtil.createResponse("FAILURE", null, e.getMessage()));
        }
    }

    @GetMapping("/stock")
    public ResponseEntity<Object> allStock() {
        logger.debug("Received request to fetch all stocks");
        try {
            List<stock> stocks = stockservice.getAllStock();
            logger.info("Successfully fetched {} stocks", stocks.size());
            return ResponseEntity.ok(ResponseUtil.createResponse("SUCCESS", stocks, null));
        } catch (RuntimeException e) {
            logger.error("Error occurred while fetching all stocks", e);
            return ResponseEntity.badRequest().body(ResponseUtil.createResponse("FAILURE", null, e.getMessage()));
        }
    }

    @GetMapping("/stock/company")
    public ResponseEntity<Object> stockByName(@RequestParam(required = false) String tickerSymbol) {
        logger.debug("Received request to fetch stocks for ticker symbol: {}", tickerSymbol);
        try {
            List<stock> stocks = stockservice.findByTickerSymbol(tickerSymbol);
            if (stocks.isEmpty()) {
                logger.warn("No stocks found for ticker symbol: {}", tickerSymbol);
                return ResponseEntity.badRequest().body(ResponseUtil.createResponse("FAILURE", null, "No stocks found for the given ticker symbol"));
            }
            logger.info("Successfully fetched {} stocks for ticker symbol: {}", stocks.size(), tickerSymbol);
            return ResponseEntity.ok(ResponseUtil.createResponse("SUCCESS", stocks, null));
        } catch (RuntimeException e) {
            logger.error("Error occurred while fetching stocks for ticker symbol: {}", tickerSymbol, e);
            return ResponseEntity.badRequest().body(ResponseUtil.createResponse("FAILURE", null, e.getMessage()));
        }
    }

    @GetMapping("/bond/company")
    public ResponseEntity<Object> bondByName(@RequestParam(required = false) String tickerSymbol) {
        logger.debug("Received request to fetch bonds for ticker symbol: {}", tickerSymbol);
        try {
            List<bond> bonds = bondservice.findByTickerSymbol(tickerSymbol);
            if (bonds.isEmpty()) {
                logger.warn("No bonds found for ticker symbol: {}", tickerSymbol);
                return ResponseEntity.badRequest().body(ResponseUtil.createResponse("FAILURE", null, "No bonds found for the given ticker symbol"));
            }
            logger.info("Successfully fetched {} bonds for ticker symbol: {}", bonds.size(), tickerSymbol);
            return ResponseEntity.ok(ResponseUtil.createResponse("SUCCESS", bonds, null));
        } catch (RuntimeException e) {
            logger.error("Error occurred while fetching bonds for ticker symbol: {}", tickerSymbol, e);
            return ResponseEntity.badRequest().body(ResponseUtil.createResponse("FAILURE", null, e.getMessage()));
        }
    }

    //utkarsh checking
}


