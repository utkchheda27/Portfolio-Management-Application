package com.neueda.portfolio.Repo;

import com.neueda.portfolio.Entity.StockData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDataRepo extends JpaRepository<StockData,Long> {
}
