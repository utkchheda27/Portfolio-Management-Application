package com.neueda.portfolio.repo;

import com.neueda.portfolio.entity.bond;
import com.neueda.portfolio.entity.stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface bondRepo extends JpaRepository<bond,Integer> {
    List<bond> findBytickerSymbol(String tickerSymbol);
}
