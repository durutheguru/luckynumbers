package com.omarze.persistence;


import com.omarze.entities.Campaign;
import com.omarze.entities.CampaignStatus;
import com.omarze.security.annotation.CanWriteCampaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * created by julian
 */
@Repository
@RepositoryRestResource(path = CampaignRepository.PATH)
public interface CampaignRepository extends JpaRepository<Campaign, Long> {


    String PATH = "campaign";


    @Override
    @RestResource(exported = false)
    Campaign save(Campaign campaign);


    @Query("SELECT CASE WHEN c.campaignStatus = 'ACTIVE' THEN TRUE ELSE FALSE END FROM Campaign c WHERE c.id = :campaignId")
    Boolean isCampaignActive(@Param("campaignId") Long campaignId);


    @Query("SELECT c FROM Campaign c WHERE c.startDate <= current_date AND c.campaignStatus = 'APPROVED'")
    Page<Campaign> findCampaignsDueForActivation(Pageable pageable);


    @Modifying
    @Query("UPDATE Campaign c SET c.campaignStatus = 'ACTIVE' WHERE c.startDate <= current_date AND c.campaignStatus = 'APPROVED'")
    void activateAllDueCampaigns();


    @Modifying
    @Query("UPDATE Campaign c SET c.campaignStatus = 'ACTIVE' WHERE c.id IN :campaignIds")
    void activateCampaigns(@Param("campaignIds") List<Long> campaignIds);


    @Query("SELECT c FROM Campaign c WHERE c.endDate <= current_date AND c.campaignStatus = 'ACTIVE'")
    Page<Campaign> findCampaignsForDeactivation(Pageable pageable);


    @Modifying
    @Query("UPDATE Campaign c SET c.campaignStatus = 'COMPLETED' WHERE c.endDate <= current_date AND c.campaignStatus = 'ACTIVE'")
    void deactivateAllDueCampaigns();


    @RestResource(path = "searchCampaign", rel = "searchCampaign")
    Page<Campaign> findByNameContainingOrDescriptionContaining(
        @Param("name") String name, @Param("desc") String description, Pageable pageable
    );


    @RestResource(path = "byStatus", rel = "byStatus")
    Page<Campaign> findByCampaignStatus(
        @Param("status") CampaignStatus status, Pageable pageable
    );


}


