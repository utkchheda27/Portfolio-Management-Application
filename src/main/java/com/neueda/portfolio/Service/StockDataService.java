package com.neueda.portfolio.Service;

import com.neueda.portfolio.Entity.Cashflow;
import com.neueda.portfolio.Entity.StockData;
import com.neueda.portfolio.Repo.StockDataRepo;
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
