package com.neueda.portfolio.Repo;

import com.neueda.portfolio.Entity.Instrument;
import com.neueda.portfolio.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstrumentRepo extends JpaRepository<Instrument, String> {
    public List<Instrument> findBytickerSymbol(String tickerSymbol);
}
