package com.neueda.portfolio.repo;

import com.neueda.portfolio.entity.StockData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDataRepo extends JpaRepository<StockData,Long> {
}
