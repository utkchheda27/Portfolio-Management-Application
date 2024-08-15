package com.neueda.portfolio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neueda.portfolio.entity.bond;
import com.neueda.portfolio.entity.stock;

import java.util.List;

@Repository
public interface stockRepo extends JpaRepository<stock,Integer> {
  List<stock> findBytickerSymbol(String tickerSymbol);
}
