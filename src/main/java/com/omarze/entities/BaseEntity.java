package com.omarze.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.omarze.util.LocalDateTimeConverter;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * created by julian
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime timeAdded;


    @LastModifiedDate
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime timeUpdated;




}
