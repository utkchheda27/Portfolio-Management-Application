package com.neueda.portfolio.Repo;

import com.neueda.portfolio.Entity.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentRepo extends JpaRepository<Instrument, String> {

}
