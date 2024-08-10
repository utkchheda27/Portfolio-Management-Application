package com.neueda.portfolio.service;

import com.neueda.portfolio.entity.Instrument;
import com.neueda.portfolio.repo.InstrumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstrumentService {

    @Autowired
    private InstrumentRepo instrumentRepo;

    public List<Instrument> getAllInstruments() {
        return instrumentRepo.findAll();
    }

    public Optional<Instrument> getInstrumentByTickerSymbol(String tickerSymbol) {
        return instrumentRepo.findById(tickerSymbol);
    }

    public Instrument saveInstrument(Instrument instrument) {
        return instrumentRepo.save(instrument);
    }

    public void deleteInstrument(String tickerSymbol) {
        instrumentRepo.deleteById(tickerSymbol);
    }
}
