package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo.models.Products;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

@Service
public class ProductsService {
    @Autowired
    private final RestTemplate restTemplate;

    public ProductsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<Products> getAllProducts() {
        String apiUrl = "https://hwangshop-cd71f-default-rtdb.firebaseio.com/products/-NvG1zudBGGqLlIsdSaF.json";

        Products[] res = restTemplate.getForObject(apiUrl, Products[].class);
        if (res == null) {
            System.err.println("API products response is null");
            return Collections.emptyList(); // return an empty list or handle appropriately
        }

        return Arrays.asList(res);
    }

    public List<Products> getByCategory(int categoryId) {
        String apiUrl = "https://hwangshop-cd71f-default-rtdb.firebaseio.com/products/-NvG1zudBGGqLlIsdSaF.json";
        List<Products> result = new ArrayList<>();

        Products[] res = restTemplate.getForObject(apiUrl, Products[].class);
        if (res == null) {
            System.err.println("API products response is null");
            return Collections.emptyList(); // return an empty list or handle appropriately
        }

        for(Products p : res) {
            if(p.getCategory() == categoryId) {
                result.add(p);
            }
        }
        return result;
    }

    public int getCategoryID(int id){
        String apiUrl = "https://hwangshop-cd71f-default-rtdb.firebaseio.com/products/-NvG1zudBGGqLlIsdSaF.json";

        Products[] res = restTemplate.getForObject(apiUrl, Products[].class);
        if (res == null) {
            System.err.println("API products response is null");
        }

        for(Products p : res) {
            if(p.getId() == id) {
                return p.getCategory();
            }
        }
        return 0;
    }

    public Products getById(int id) {
        Products result;
        String apiUrl = "https://hwangshop-cd71f-default-rtdb.firebaseio.com/products/-NvG1zudBGGqLlIsdSaF.json";

        Products[] res = restTemplate.getForObject(apiUrl, Products[].class);
        if (res == null) {
            System.err.println("API products response is null");
        }

        for (Products product : res) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
