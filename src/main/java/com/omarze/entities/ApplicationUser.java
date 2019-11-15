package com.omarze.entities;


import com.omarze.Constants;
import org.springframework.security.core.userdetails.User;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;

/**
 * created by julian
 */
@MappedSuperclass
public class ApplicationUser extends BaseEntity {

    @NotEmpty(message = "Name is required")
    @Size(max = 100, message = "Maximum Name length is {max}")
    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100)
    @Size(max = 100, message = "Email length cannot exceed {max}")
    @Pattern(regexp = Constants.Patterns.EMAIL, message = "Email is invalid")
    private String email;

    @Column(nullable = false, length = 100, unique = true)
    @NotEmpty(message = "Username is required")
    @Size(max = 100, message = "Username should not exceed {max} characters")
    private String username;

    @Column(nullable = false, length = 200)
    @NotEmpty(message = "Password is required")
    @Size(max = 200, message = "Password length is too long")
    private String password;


    public String getName() {
        return name;
    }

    public ApplicationUser setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ApplicationUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ApplicationUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ApplicationUser setPassword(String password) {
        this.password = password;
        return this;
    }


    public User toUser() {
        return new User(username, password, new ArrayList<>());
    }


}
