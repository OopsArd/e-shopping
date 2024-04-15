package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetail {
    @JsonProperty("productId")
    private int productId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("price")
    private double price;

    public OrderDetail(int productId, String name, int amount, double price) {
        this.productId = productId;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

}
