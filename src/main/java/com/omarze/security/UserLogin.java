package com.omarze.security;


/**
 * created by julian
 */
public class UserLogin {


    public String username;

    public String password;

    public UserLogin() {}

    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public UserLogin setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLogin setPassword(String password) {
        this.password = password;
        return this;
    }


}
