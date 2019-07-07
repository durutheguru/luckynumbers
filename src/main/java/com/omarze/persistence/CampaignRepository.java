package com.omarze.persistence;


import com.omarze.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * created by julian
 */
@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {


    @Query("SELECT c.active FROM Campaign c WHERE c.id = :campaignId")
    Boolean isCampaignActive(@Param("campaignId") Long campaignId);


}
