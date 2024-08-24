package com.neueda.portfolio.repo;


import com.neueda.portfolio.entity.stock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
public interface stockRepo extends JpaRepository<stock,Integer> {
  List<stock> findBytickerSymbol(String tickerSymbol);
}
