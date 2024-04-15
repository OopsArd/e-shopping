package com.example.demo.controller;

import com.example.demo.models.Categories;
import com.example.demo.models.Products;
import com.example.demo.service.CategoriesService;
import com.example.demo.service.ProductsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CategoriesService categoriesService;
    private final ProductsService productsService;

    public HomeController(CategoriesService categoriesService, ProductsService productsService) {
        this.categoriesService = categoriesService;
        this.productsService = productsService;
    }

    @RequestMapping("/")
    public String home(Model model) {
        List<Categories> categories = categoriesService.getAllCategories();
        List<Products> products = productsService.getAllProducts();

        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping("/products/category/{id}")
    public String getProductsCategoryId(Model model, @PathVariable int id) {
        List<Products> products = productsService.getByCategory(id);
        List<Categories> categories = categoriesService.getAllCategories();


        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "index";
    }

}
