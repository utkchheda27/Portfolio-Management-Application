package com.neueda.portfolio.Repo;

import com.neueda.portfolio.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders, Long> {
    public List<Orders> findBytickerSymbol(String tickerSymbol);git
}
