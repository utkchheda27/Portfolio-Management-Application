package com.neueda.portfolio.Service;

import com.neueda.portfolio.Entity.stock;
import com.neueda.portfolio.Repo.stockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class stockService {

    @Autowired
    private stockRepo stockrepo;


    // For getting all the stock
    public List<stock> getAllStock(){
        return stockrepo.findAll();
    }

    public List<stock> findByTickerSymbol(String tickerSymbol){
        return stockrepo.findBytickerSymbol(tickerSymbol);
    }
}
