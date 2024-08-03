package com.neueda.portfolio.Repo;

import com.neueda.portfolio.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders, Long> {
}
