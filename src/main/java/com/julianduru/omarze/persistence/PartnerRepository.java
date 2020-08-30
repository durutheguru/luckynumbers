package com.julianduru.omarze.persistence;


import com.julianduru.omarze.entities.Partner;
import com.julianduru.omarze.security.annotation.IsBackOfficeOrPartnerUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * created by julian
 */
@Repository
@IsBackOfficeOrPartnerUser
@RepositoryRestResource(path = PartnerRepository.PATH)
public interface PartnerRepository extends JpaRepository<Partner, Long> {


    String PATH = "partner";


    Optional<Partner> findByName(String name);


    boolean existsByName(String name);


    @RestResource(path = "searchName", rel = "searchName")
    Page<Partner> findByNameContaining(
        @Param("name") String name,
        Pageable pageable
    );


    Optional<Partner> findByDescription(String description);


    Optional<Partner> findFirstBy();


    @RestResource
    List<Partner> findBy(Sort sort);


}

