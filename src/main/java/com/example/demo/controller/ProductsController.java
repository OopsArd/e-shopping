package com.example.demo.controller;

import com.example.demo.service.CategoriesService;
import org.springframework.stereotype.Controller;
import com.example.demo.service.ProductsService;
import com.example.demo.models.Products;
import com.example.demo.models.Categories;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
public class ProductsController {

    private final ProductsService productsService;
    private final CategoriesService categoriesService;

    public ProductsController(ProductsService productsService, CategoriesService categoriesService) {
        this.productsService = productsService;
        this.categoriesService = categoriesService;
    }


    //  @GetMapping("/products/detail/{id}"): khi google chuyển sang đường link này thì hàm bên dưới được sử dụng
    // @PathVariable int id lấy giá trị của biến nằm trong dấu { } trong @GetMapping
    // Model lưu dữ liệu để file html được liên kết có thể sử dụng

    // productsService.getById(id): kêu productsService chạy hàm getById và truyền id lấy được vào
    // Products products: lưu gi trị mà productsService trả về sau khi chạy xong

    // model.addAttribute("products", products): lưu thằng 'products' vào model
    //return "/products/detail": liên kết model với file '/products/detail.html'

    @GetMapping("/products/detail/{id}")
    public String getProductsId(Model model, @PathVariable int id) {
        Products products= productsService.getById(id);
        int categoryId = productsService.getCategoryID(id);
        List<Products> sameCategory = productsService.getByCategory(categoryId);

        List<Categories> categories = categoriesService.getAllCategories();

        model.addAttribute("products", products);
        model.addAttribute("sameCategory", sameCategory);
        model.addAttribute("categories", categories);
        return "/products/detail";
    }


}
