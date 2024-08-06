package com.neueda.portfolio.repo;

import com.neueda.portfolio.entity.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentRepo extends JpaRepository<Instrument, String> {

}
