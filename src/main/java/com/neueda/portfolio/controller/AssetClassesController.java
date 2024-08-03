package com.neueda.portfolio.controller;


import com.neueda.portfolio.entity.bond;
import com.neueda.portfolio.entity.stock;
import com.neueda.portfolio.service.bondService;
import com.neueda.portfolio.service.stockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AssetClassesController {

    public AssetClassesController() {
        System.out.println("Constructor called");
    }

    @Autowired
    private stockService stockservice;

    @Autowired
    private bondService bondservice;

    @GetMapping("/bond")
    public List<bond> AllBond() {
        System.out.println("All bond");
        return bondservice.getAllBond();
    }

    @GetMapping("/stock")
    public List<stock> AllStock() {
        System.out.println("All stock");
        return stockservice.getAllStock();
    }

    // http://localhost:8080/stock/company?tickerSymbol=AAPL
    @GetMapping("/stock/company")
    public List<stock> stockByName(@RequestParam(required = false) String tickerSymbol){
        return stockservice.findByTickerSymbol(tickerSymbol);
    }
    @GetMapping("/bond/company")
    public List<bond> bondByName(@RequestParam(required = false) String tickerSymbol){
        return bondservice.findByTickerSymbol(tickerSymbol);
    }

}