package com.neueda.portfolio.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neueda.portfolio.entity.Orders;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders, Long> {
    public List<Orders> findBytickerSymbol(String tickerSymbol);
    
  /*  //to fetch the list of assets
    Orders findFirstByTickerSymbolOrderByTransactionDateAsc(String tickerSymbol);
*/
}
