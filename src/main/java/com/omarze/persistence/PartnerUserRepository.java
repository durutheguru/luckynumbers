package com.omarze.persistence;


import com.omarze.entities.PartnerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * created by julian
 */
@Repository
public interface PartnerUserRepository extends JpaRepository<PartnerUser, Long> {


    Optional<PartnerUser> findByUsername(String username);


    Optional<PartnerUser> findByUsernameAndPassword(String username, String password);


    Boolean existsByUsername(String username);


    Boolean existsByEmail(String email);


}
