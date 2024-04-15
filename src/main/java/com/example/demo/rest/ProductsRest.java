package com.example.demo.rest;

import com.example.demo.models.Products;
import com.example.demo.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductsRest {

    @Autowired
    private ProductsService productsService;

    @RequestMapping("/products/{id}")
    public ResponseEntity<Products> getProduct(@PathVariable("id") int id) {
        Products product = productsService.getById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            // Return a 404 Not Found status if the product is not found
            return ResponseEntity.notFound().build();
        }
    }
}