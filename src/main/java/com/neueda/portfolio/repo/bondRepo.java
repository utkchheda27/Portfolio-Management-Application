package com.neueda.portfolio.Repo;

import com.neueda.portfolio.Entity.bond;
import com.neueda.portfolio.Entity.stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface bondRepo extends JpaRepository<bond,Integer> {
    List<bond> findBytickerSymbol(String tickerSymbol);
}
