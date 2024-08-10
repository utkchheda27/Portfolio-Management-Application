
package com.neueda.portfolio.controller;

import com.neueda.portfolio.entity.bond;
import com.neueda.portfolio.entity.stock;
import com.neueda.portfolio.service.bondService;
import com.neueda.portfolio.service.stockService;
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
}
