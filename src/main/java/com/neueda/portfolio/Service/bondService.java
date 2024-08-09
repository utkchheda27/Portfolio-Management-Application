package com.neueda.portfolio.Service;

import com.neueda.portfolio.Entity.bond;
import com.neueda.portfolio.Entity.stock;
import com.neueda.portfolio.Repo.bondRepo;
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
