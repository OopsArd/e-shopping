package com.example.demo.controller;

import com.example.demo.models.Categories;
import com.example.demo.service.CategoriesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CartController {
    private final CategoriesService categoriesService;
    public CartController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @RequestMapping("/cart-detail")
    public String cart(Model model) {
        List<Categories> categories = categoriesService.getAllCategories();
        model.addAttribute("categories", categories);

        return "cart/cart-detail";
    }
}
