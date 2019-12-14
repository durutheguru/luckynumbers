package com.omarze.entities;


import com.omarze.Constants;
import lombok.Data;
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
@Data
@MappedSuperclass
public class ApplicationUser extends BaseEntity {


    @Column(length = 100, nullable = false)
    private String name;


    @Column(length = 100, unique = true)
    private String email;


    @Column(nullable = false, length = 100, unique = true)
    private String username;


    @Column(nullable = false, length = 200)
    private String password;


    public User toUser() {
        return new User(username, password, new ArrayList<>());
    }


}
