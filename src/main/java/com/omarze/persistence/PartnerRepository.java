package com.omarze.persistence;


import com.omarze.entities.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * created by julian
 */
@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {



}
