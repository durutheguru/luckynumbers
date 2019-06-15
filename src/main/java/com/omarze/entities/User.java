package com.omarze.entities;


import com.omarze.Constants;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * created by julian
 */
@MappedSuperclass
public class User extends BaseEntity {

    @NotEmpty(message = "Name is required")
    @Size(max = 100, message = "Maximum Name length is {max}")
    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100)
    @Size(max = 100, message = "Email length cannot exceed {max}")
    @Pattern(regexp = Constants.Patterns.EMAIL, message = "Email is invalid")
    private String email;

    @Column(nullable = false)
    private String username;

    private String password;


    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }


}
