package com.neueda.portfolio.controller;

import com.neueda.portfolio.entity.StockData;
import com.neueda.portfolio.repo.StockDataRepo;
import com.neueda.portfolio.service.StockDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stockdata")
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class StockDataController {
    @Autowired
    private StockDataService stockDataService;
    @GetMapping("/all")
    public List<StockData> orderBook(){
        return stockDataService.getStockData();
    }

}
