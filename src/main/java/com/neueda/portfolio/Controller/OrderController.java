package com.neueda.portfolio.Controller;


import com.neueda.portfolio.Entity.Orders;
import com.neueda.portfolio.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Orders> getBooks() {
        return orderService.getAllOrders();
    }


}
