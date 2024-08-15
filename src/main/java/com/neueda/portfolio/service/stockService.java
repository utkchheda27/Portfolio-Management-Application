package com.neueda.portfolio.service;

import com.neueda.portfolio.repo.stockRepo;
import com.neueda.portfolio.entity.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class stockService {

    @Autowired
    private stockRepo stockRepo;

    public List<stock> getAllStock(){
        return stockRepo.findAll();
    }

    public List<stock> findByTickerSymbol(String tickerSymbol){
        return stockRepo.findBytickerSymbol(tickerSymbol);
    }
}
