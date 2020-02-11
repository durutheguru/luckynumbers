package com.omarze.api.dto;


import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * created by julian
 */
public abstract class AbstractResourceAssembler<T, D extends ResourceSupport> extends ResourceAssemblerSupport<T, D> {


    protected Class<?> controllerKlass;


    public AbstractResourceAssembler(Class<?> controllerClass, Class<D> resourceType) {
        super(controllerClass, resourceType);
        this.controllerKlass = controllerClass;
    }


}
