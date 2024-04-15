package com.example.demo.controller;

import com.example.demo.models.Categories;
import com.example.demo.models.OrderDetail;
import com.example.demo.models.Orders;
import com.example.demo.service.AccountService;
import com.example.demo.service.CategoriesService;
import com.example.demo.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller()
public class OderController {
    private final CategoriesService categoriesService;
    private final AccountService accountService;
    private final OrderService orderService;

    public OderController(CategoriesService categoriesService, AccountService accountService, OrderService orderService) {
        this.categoriesService = categoriesService;
        this.accountService = accountService;
        this.orderService = orderService;
    }

    @RequestMapping("/order/checkout")
    public String checkout(Model model) {
        List<Categories> categories = categoriesService.getAllCategories();
        model.addAttribute("categories", categories);

        String userInfo = accountService.getCurrentUser();

        model.addAttribute("userInfo", userInfo);
        return "order/checkout";
    }

    @RequestMapping("/order/list")
    public String list(Model model) {
        List<Categories> categories = categoriesService.getAllCategories();
        model.addAttribute("categories", categories);

        List<Orders> listOrder = orderService.findAll();
        model.addAttribute("listOrder", listOrder);
        return "order/list";
    }

    @RequestMapping("/order/detail/{id}")
    public String detail(Model model, @PathVariable int id) {
        List<Categories> categories = categoriesService.getAllCategories();
        model.addAttribute("categories", categories);

        Orders x = orderService.findById(id);
        List<OrderDetail> detail = x.getDetail();

        model.addAttribute("order", x);
        model.addAttribute("detail", detail);
        return "order/detail";
    }
}
