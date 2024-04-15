package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo.models.Categories;

import java.util.Collections;
import java.util.List;
import java.util.Arrays;

@Service
public class CategoriesService {
    @Autowired
    private final RestTemplate restTemplate;

    public CategoriesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<Categories> getAllCategories() {
        String apiUrl = "https://hwangshop-cd71f-default-rtdb.firebaseio.com/categories/-NvD8jUeUBltYEtiEFib.json";

        Categories[] rs = restTemplate.getForObject(apiUrl, Categories[].class);

        if (rs == null) {
            System.err.println("API category response is null");
            return Collections.emptyList(); // return an empty list or handle appropriately
        }

        return Arrays.asList(rs);
    }
}
