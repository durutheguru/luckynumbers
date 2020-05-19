package com.omarze.persistence;


import com.omarze.entities.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * created by julian
 */
@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {


    Optional<Partner> findByName(String name);


    Optional<Partner> findByDescription(String description);


    Optional<Partner> findFirstBy();


}
