package com.omarze.api.resource;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.omarze.util.MapperUtil;
import org.springframework.hateoas.Resources;

import java.util.Collection;

/**
 * created by julian
 *
 * * @param <C, T, D> - C: (Controller Class), T: (Entity), D: (data or DTO)
 *
 */
public class ResourceList <T, D, C> extends Resources<D> {


    @JsonIgnore
    protected Class<C> controllerKlass;


    public ResourceList(Collection<T> entities, Class<D> dtoClass, Class<C> controllerKlass) {
        super(MapperUtil.map(entities, dtoClass));
        this.controllerKlass = controllerKlass;
    }


}

