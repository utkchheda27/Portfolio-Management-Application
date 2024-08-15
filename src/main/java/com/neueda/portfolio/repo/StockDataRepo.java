package com.neueda.portfolio.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neueda.portfolio.entity.StockData;

public interface StockDataRepo extends JpaRepository<StockData,Long> {
}
