package com.omarze.api.resource;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.omarze.controller.api.BaseApiController;
import com.omarze.util.MapperUtil;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedResources;

/**
 * created by julian
 *
 * @param <C, T, D> - C: (Controller Class), T: (Entity), D: (data or DTO)
 *
 */
@Getter
public class ResourcePage<T, D, C extends BaseApiController> extends PagedResources<D> {


    @JsonIgnore
    protected Class<C> controllerKlass;


    public ResourcePage(Page<T> entities, Class<D> dtoClass, Class<C> controllerKlass) {
        super(
            MapperUtil.map(entities.getContent(), dtoClass),
            new PageMetadata(entities.getSize(), entities.getNumber(), entities.getTotalElements())
        );

        this.controllerKlass = controllerKlass;
    }


    private void init() {

    }


}
