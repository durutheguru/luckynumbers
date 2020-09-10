package com.julianduru.omarze.persistence;


import com.julianduru.omarze.entities.LotteryUser;
import com.julianduru.omarze.security.annotation.IsBackOfficeOrLotteryUser;
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
@IsBackOfficeOrLotteryUser
@RepositoryRestResource(path = LotteryUserRepository.PATH)
public interface LotteryUserRepository extends JpaRepository<LotteryUser, Long> {


    String PATH = "lottery_user";


    Optional<LotteryUser> findByUsername(String username);


    Optional<LotteryUser> findByUsernameAndPassword(String username, String password);


    Optional<LotteryUser> findFirstBy();


    Boolean existsByUsername(String username);


    Boolean existsByEmail(String email);


    @RestResource(path = "searchUsers", rel="searchUsers")
    Page<LotteryUser> findByNameContainingOrUsernameContaining(
        @Param("name") String name,
        @Param("username") String username,
        Pageable pageable
    );


}

