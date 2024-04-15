package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Accounts {
    @JsonProperty("id")
    private int id;

    @JsonProperty("role")
    private String role;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    public Accounts() {}

    public Accounts(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pass='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
