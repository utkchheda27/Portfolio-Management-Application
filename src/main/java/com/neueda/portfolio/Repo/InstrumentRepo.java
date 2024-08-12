package com.neueda.portfolio.repo;

import com.neueda.portfolio.entity.Instrument;
import com.neueda.portfolio.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstrumentRepo extends JpaRepository<Instrument, String> {
    public List<Instrument> findBytickerSymbol(String tickerSymbol);
}
