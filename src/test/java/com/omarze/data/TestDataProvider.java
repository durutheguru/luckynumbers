package com.omarze.data;


import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * created by julian
 */
@Getter
@RequiredArgsConstructor
public abstract class TestDataProvider<E, R extends JpaRepository<E, ?>> implements DataProvider<E> {


    private final R repository;


    protected final Faker faker = new Faker();


    public List<E> loadPersistedEntities() {
        return repository.findAll();
    }


}

