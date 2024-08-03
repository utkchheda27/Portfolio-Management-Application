package com.neueda.portfolio.Service;


import com.neueda.portfolio.Entity.Instrument;
import com.neueda.portfolio.Entity.Orders;
import com.neueda.portfolio.Repo.CashflowRepo;
import com.neueda.portfolio.Repo.InstrumentRepo;
import com.neueda.portfolio.Repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private InstrumentRepo instrumentRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CashflowRepo cashflowRepo;


    public List<Orders> getAllOrders(){
        return orderRepo.findAll();
    }

}
