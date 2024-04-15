package com.example.demo.service;

import com.example.demo.models.Orders;
import com.example.demo.models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private final RestTemplate restTemplate;

    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Orders create(Orders orderData) {
        String apiUrl = "https://hwangshop-cd71f-default-rtdb.firebaseio.com/orders/-NvSQ82wPdErrol6JkvF.json";
        int maxID = 0;

        // lấy danh sách order hiện có trong firebase
        Orders[] res = restTemplate.getForObject(apiUrl, Orders[].class);

        // kiểm tra chắc chắn rằng res khác null
        assert res != null;

        // lần lượt tất cả order để tìm ra id lớn nhất
        for(Orders order : res) {
            if(order.getId() > maxID) {
                maxID = order.getId();
            }
        }

        // tạo id cho order mới đặt bằng cách tăng id lớn nhất thêm 1
        orderData.setId(maxID + 1);

        // tạo mảng các order mới, từ mảng các order lấy từ firebase
        Orders[] newList = Arrays.copyOf(res, res.length + 1);

        // thêm order mới được tạo vào mảng order
        newList[res.length] = orderData;


        // Thiết lập tiêu đề HTTP để chỉ định định dạng JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Tạo đối tượng HttpEntity với dữ liệu orderData và tiêu đề
        HttpEntity<Orders[]> request = new HttpEntity<>(newList, headers);

        // PUT lên firebase
        restTemplate.put(apiUrl, request);

        return orderData;
    }

    public Orders findById(int id) {
        String apiUrl = "https://hwangshop-cd71f-default-rtdb.firebaseio.com/orders/-NvSQ82wPdErrol6JkvF.json";
        Orders[] res = restTemplate.getForObject(apiUrl, Orders[].class);

        if(res != null){
            for(Orders order : res) {
                if(order.getId() == id) {
                    return order;
                }
            }
        }

        return null;
    }

    public List<Orders> findAll() {
        String apiUrl = "https://hwangshop-cd71f-default-rtdb.firebaseio.com/orders/-NvSQ82wPdErrol6JkvF.json";
        Orders[] res = restTemplate.getForObject(apiUrl, Orders[].class);

        if (res == null) {
            System.err.println("API order response is null");
            return Collections.emptyList(); // return an empty list or handle appropriately
        }

        return Arrays.asList(res);
    }
}
