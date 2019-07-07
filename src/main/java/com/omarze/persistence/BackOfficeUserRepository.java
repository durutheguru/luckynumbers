package com.omarze.persistence;


import com.omarze.entities.BackOfficeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * created by julian
 */
@Repository
public interface BackOfficeUserRepository extends JpaRepository<BackOfficeUser, Long> {


    Optional<BackOfficeUser> findByUsername(String username);

    Optional<BackOfficeUser> findByUsernameAndPassword(String username, String password);


}
