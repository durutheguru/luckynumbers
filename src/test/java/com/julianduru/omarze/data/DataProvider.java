package com.julianduru.omarze.data;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * created by julian
 */
public interface DataProvider<E> {


    E newEntity();


    JpaRepository<E, ?> getRepository();


    default List<E> newEntities(int count) {
        List<E> entityList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            entityList.add(newEntity());
        }

        return entityList;
    }


    default E saveEntity() {
        E entity = newEntity();
        return getRepository().save(entity);
    }


}
