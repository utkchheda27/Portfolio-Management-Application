package com.neueda.portfolio.controller;

import com.neueda.portfolio.service.bondService;
import com.neueda.portfolio.service.stockService;
import com.neueda.portfolio.entity.bond;
import com.neueda.portfolio.entity.stock;
import com.neueda.portfolio.exception.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class AssetClassesController {

    private static final Logger logger = LoggerFactory.getLogger(AssetClassesController.class);

    @Autowired
    private stockService stockService;

    @Autowired
    private bondService bondService;

    @Autowired
    private ResponseUtil responseutil;

    @GetMapping("/bond")
    public ResponseEntity<Object> allBond() {
        logger.debug("Received request to fetch all bonds");
        try {
            List<bond> bonds = bondService.getAllBond();
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
            List<stock> stocks = stockService.getAllStock();
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
            List<stock> stocks = stockService.findByTickerSymbol(tickerSymbol);
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
            List<bond> bonds = bondService.findByTickerSymbol(tickerSymbol);
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


