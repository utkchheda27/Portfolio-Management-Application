package com.neueda.portfolio.repo;

import com.neueda.portfolio.entity.Cashflow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashflowRepo  extends JpaRepository<Cashflow, Long> {
         public List<Cashflow> findBytickerSymbol(String tickerSymbol);
}
