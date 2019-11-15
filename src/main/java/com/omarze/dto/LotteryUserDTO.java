package com.omarze.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * created by julian
 */
public class LotteryUserDTO extends BaseDTO {


    private String name;

    private String email;

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    public String getName() {
        return name;
    }

    public LotteryUserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public LotteryUserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LotteryUserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LotteryUserDTO setPassword(String password) {
        this.password = password;
        return this;
    }


}
