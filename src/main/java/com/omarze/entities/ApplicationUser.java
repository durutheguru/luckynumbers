package com.omarze.entities;


import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by julian
 */
@Data
@MappedSuperclass
public abstract class ApplicationUser extends BaseEntity {


    @Column(length = 100, nullable = false)
    private String name;


    @Column(length = 100, unique = true)
    private String email;


    @Column(nullable = false, length = 100, unique = true)
    private String username;


    @Column(nullable = false, length = 200)
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
