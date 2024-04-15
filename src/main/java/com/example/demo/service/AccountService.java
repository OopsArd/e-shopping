package com.example.demo.service;

import com.example.demo.models.Accounts;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {
    public final RestTemplate restTemplate;

    public AccountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Accounts findByUsername(String username){
        System.out.println("username nhan duoc o service: " + username);
        System.out.println("service running..... ");
        String apiUrl = "https://hwangshop-cd71f-default-rtdb.firebaseio.com/users/-NvHfU7MfpDGA3v1y2VF.json";

        Accounts[] res = restTemplate.getForObject(apiUrl, Accounts[].class);
        if (res == null) {
            System.err.println("API account response is null");
        }

        for (Accounts acc : res) {
            System.out.println("username cua res : " + acc.getUsername());
            if (acc.getUsername().equals(username)) {
                System.out.println("res tra ve: " + acc.toString());
                return acc;
            }
        }
        return null;
    };

    public UserDetails getCurrentUserDetails() {
        // Lấy thông tin xác thực của người dùng hiện tại
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Kiểm tra xem thông tin có phải là UserDetails không
        if (principal instanceof UserDetails) {
            return (UserDetails) principal;
        } else {
            return null;
        }
    }

    public String getCurrentUser() {

        UserDetails userDetails = getCurrentUserDetails();
        if (userDetails != null) {
            return userDetails.getUsername();

        }
        return "";
    }
}
