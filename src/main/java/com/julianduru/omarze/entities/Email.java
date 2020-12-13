package com.julianduru.omarze.entities;


import com.julianduru.security.entity.BaseEntity;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * created by julian
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Email extends BaseEntity implements Serializable {


    @Column(nullable = false, unique = true, length = 100)
    private String reference;


    @Column(nullable = false, length = 100)
    private String sender;


    @Column(nullable = false, length = 200)
    private String title;


    @NotEmpty(message = "Email Recipients must not be empty")
    @ElementCollection
    private List<String> recipients;


    @ElementCollection
    private List<String> ccs;


    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EmailType emailType;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EmailStatus emailStatus;


    @Override
    public String toString() {
        return String.format("{sender=%s,title=%s,message=%s,emailType=%s}",
            getSender(), getTitle(), getMessage(), getEmailType().toString()
        );
    }


}

