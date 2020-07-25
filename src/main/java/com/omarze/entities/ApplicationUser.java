package com.omarze.entities;


import com.julianduru.security.entity.BaseEntity;
import com.omarze.Constants;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by julian
 */
@Data
@MappedSuperclass
public abstract class ApplicationUser extends BaseEntity {


    @Column(length = 100, nullable = false)
    @NotEmpty(message = "Name is required")
    @Size(max = 100, message = "Maximum Name length is {max}")
    private String name;


    @Column(length = 100, unique = true)
    @NotEmpty(message = "Email is required")
    @Size(max = 100, message = "Email length cannot exceed {max}")
    @Pattern(regexp = Constants.Patterns.EMAIL, message = "Email is invalid")
    private String email;


    @Column(nullable = false, length = 100, unique = true)
    @NotEmpty(message = "Username is required")
    @Size(max = 100, message = "Username should not exceed {max} characters")
    private String username;


    @Column(nullable = false, length = 200)
    @NotEmpty(message = "Password is required")
    @Size(max = 100, message = "Password length is too long")
    private String password;


    protected abstract List<String> roles();


    public User toUser() {
        return new User(
            username,
            password,
            roles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
        );
    }


}
