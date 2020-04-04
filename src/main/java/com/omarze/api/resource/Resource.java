package com.omarze.api.resource;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.omarze.api.dto.BaseDTO;
import com.omarze.controller.api.BaseApiController;
import com.omarze.exception.ServiceException;
import com.omarze.util.MapperUtil;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * created by julian
 *
 * @param <T, D, C> - C: (Controller Class), T: (Entity), D: (data or DTO)
 *
 */
@Getter
public abstract class Resource<T, D extends BaseDTO, C extends BaseApiController> extends ResourceSupport {


    @JsonIgnore
    protected Class<C> controllerKlass;


    protected D resource;


    public Resource(T entity, Class<D> dtoClass, Class<C> controller) throws ServiceException {
        this.controllerKlass = controller;
        this.resource = MapperUtil.map(entity, dtoClass);
        init();
    }


    private void init() throws ServiceException {
        add(linkTo(methodOn(controllerKlass).getById(resource.getId())).withSelfRel());
    }


}
