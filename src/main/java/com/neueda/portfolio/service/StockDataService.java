package com.neueda.portfolio.service;

import com.neueda.portfolio.repo.StockDataRepo;
import com.neueda.portfolio.entity.Cashflow;
import com.neueda.portfolio.entity.StockData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StockDataService {


    @Autowired
    private StockDataRepo stockDataRepo;
    public List<StockData> getStockData(){
        return stockDataRepo.findAll();

    }
}
