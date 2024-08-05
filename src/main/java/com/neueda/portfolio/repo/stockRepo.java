package com.neueda.portfolio.Repo;

import com.neueda.portfolio.Entity.bond;
import com.neueda.portfolio.Entity.stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface stockRepo extends JpaRepository<stock,Integer> {
  List<stock> findBytickerSymbol(String tickerSymbol);
}
