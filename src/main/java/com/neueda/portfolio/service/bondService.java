package com.neueda.portfolio.service;

import com.neueda.portfolio.entity.bond;
import com.neueda.portfolio.repo.bondRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class bondService {
    @Autowired
    private bondRepo bondrepo;

    public List<bond> getAllBond(){
        return bondrepo.findAll();
    }
    public List<bond> findByTickerSymbol(String tickerSymbol){
        return bondrepo.findBytickerSymbol(tickerSymbol);
    }
}
