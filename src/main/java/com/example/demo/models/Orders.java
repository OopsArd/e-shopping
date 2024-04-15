package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Orders {
    @JsonProperty("id")
    private int id;

    @JsonProperty("address")
    private String address;

    @JsonProperty("orderDate")
    private String orderDate;

    @JsonProperty("username")
    private String username;

    @JsonProperty("detail")
    private List<OrderDetail> detail;

    public Orders (int id, String address, String orderDate, String username, List<OrderDetail> detail) {
        this.id = id;
        this.address = address;
        this.orderDate = orderDate;
        this.username = username;
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

}
