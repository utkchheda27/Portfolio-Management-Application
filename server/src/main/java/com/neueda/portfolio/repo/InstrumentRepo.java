package com.neueda.portfolio.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neueda.portfolio.entity.Instrument;

import java.util.List;

public interface InstrumentRepo extends JpaRepository<Instrument, String> {
    public List<Instrument> findBytickerSymbol(String tickerSymbol);
    public List<Instrument> findBytype(String type);
}
