package com.omarze.persistence;


import com.omarze.entities.PartnerUser;
import com.omarze.security.annotation.IsBackOfficeUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * created by julian
 */
@Repository
@IsBackOfficeUser
@RepositoryRestResource(path = PartnerUserRepository.PATH)
public interface PartnerUserRepository extends JpaRepository<PartnerUser, Long> {


    String PATH = "partner_user";


    Optional<PartnerUser> findByUsername(String username);


    Optional<PartnerUser> findByUsernameAndPassword(String username, String password);


    Boolean existsByUsername(String username);


    Boolean existsByEmail(String email);


    Optional<PartnerUser> findFirstBy();


    @RestResource(path = "searchUsers", rel = "searchUsers")
    Page<PartnerUser> findByNameContainingOrUsernameContaining(
        @Param("name") String name,
        @Param("username") String username,
        Pageable pageable
    );


}


