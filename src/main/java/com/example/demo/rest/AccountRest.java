package com.example.demo.rest;

import com.example.demo.models.Accounts;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountRest {

    @Autowired
    private AccountService accountService;

    @GetMapping("/account/{username}")
    public Map<String, Accounts> getAccount(@PathVariable("username") String username) {
        Map<String, Accounts> response = new HashMap<>();
        Accounts accounts = accountService.findByUsername(username);
        response.put("data", accounts);
        return response;
    }

}
