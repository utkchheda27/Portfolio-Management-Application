package com.neueda.portfolio.controller;

import com.neueda.portfolio.entity.Instrument;
import com.neueda.portfolio.service.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instruments")
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class InstrumentController {

    @Autowired
    private InstrumentService instrumentService;

    @GetMapping
    public List<Instrument> getAllInstruments() {
        return instrumentService.getAllInstruments();
    }

    @GetMapping("/{tickerSymbol}")
    public ResponseEntity<Instrument> getInstrumentByTickerSymbol(@PathVariable String tickerSymbol) {
        Optional<Instrument> instrument = instrumentService.getInstrumentByTickerSymbol(tickerSymbol);
        return instrument.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Instrument createInstrument(@RequestBody Instrument instrument) {
        return instrumentService.saveInstrument(instrument);
    }

    @DeleteMapping("/{tickerSymbol}")
    public ResponseEntity<Void> deleteInstrument(@PathVariable String tickerSymbol) {
        instrumentService.deleteInstrument(tickerSymbol);
        return ResponseEntity.noContent().build();
    }
}
