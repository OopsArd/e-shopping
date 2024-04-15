package com.example.demo.rest;

import com.example.demo.models.Products;
import com.example.demo.service.OrderService;
import com.example.demo.models.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderRest {

    @Autowired
    OrderService orderService;

    @PostMapping()
    public ResponseEntity<Orders> createOrder(@RequestBody Orders orderData) {
        Orders order = orderService.create(orderData);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            // Return a 404 Not Found status if the product is not found
            return ResponseEntity.notFound().build();
        }
    }
}
