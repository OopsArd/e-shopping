package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Products {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private double price;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("img")
    private String img;

    @JsonProperty("category")
    private int category;

    public Products() {}

    public Products(int id, String name, double price, int amount, String img, int category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.img = img;
        this.category = category;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

}
