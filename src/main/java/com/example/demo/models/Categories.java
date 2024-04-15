package com.example.demo.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Categories {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    public Categories() {}

    public Categories(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                // Include other fields in the string representation
                '}';
    }
}
