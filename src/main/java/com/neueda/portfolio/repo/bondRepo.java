package com.neueda.portfolio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neueda.portfolio.entity.bond;

import java.util.List;

@Repository
public interface bondRepo extends JpaRepository<bond,Integer> {
    List<bond> findBytickerSymbol(String tickerSymbol);
}
