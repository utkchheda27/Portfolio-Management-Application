package com.neueda.portfolio.repo;

import com.neueda.portfolio.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders, Long> {
    public List<Orders> findBytickerSymbol(String tickerSymbol);
}
