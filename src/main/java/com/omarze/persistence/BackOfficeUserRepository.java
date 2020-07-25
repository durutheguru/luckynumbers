package com.omarze.persistence;


import com.omarze.entities.BackOfficeUser;
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
@RepositoryRestResource(path = BackOfficeUserRepository.PATH)
public interface BackOfficeUserRepository extends JpaRepository<BackOfficeUser, Long> {

    String PATH = "back_office_user";


    Boolean existsByUsername(String username);


    Optional<BackOfficeUser> findByUsername(String username);


    Optional<BackOfficeUser> findByUsernameAndPassword(String username, String password);


    Boolean existsByEmail(String email);


    Optional<BackOfficeUser> findFirstBy();


    @RestResource(path = "searchUsers", rel="searchUsers")
    Page<BackOfficeUser> findByNameContainingOrUsernameContaining(
        @Param("name") String name,
        @Param("username") String username,
        Pageable pageable
    );


}

