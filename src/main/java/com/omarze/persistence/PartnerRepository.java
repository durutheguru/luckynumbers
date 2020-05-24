package com.omarze.persistence;


import com.omarze.entities.Partner;
import com.omarze.security.annotation.IsBackOfficeUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * created by julian
 */
@Repository
@IsBackOfficeUser
@RepositoryRestResource(path = PartnerRepository.PATH)
public interface PartnerRepository extends JpaRepository<Partner, Long> {


    String PATH = "/partner";



    Optional<Partner> findByName(String name);


    Optional<Partner> findByDescription(String description);


    Optional<Partner> findFirstBy();


    @RestResource
    List<Partner> findBy(Sort sort);


}

