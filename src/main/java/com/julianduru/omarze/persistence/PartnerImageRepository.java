package com.julianduru.omarze.persistence;


import com.julianduru.omarze.entities.PartnerImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * created by julian
 */
@Repository
public interface PartnerImageRepository extends JpaRepository<PartnerImage, Long> {



}



