package com.omarze.persistence;


import com.omarze.entities.LotteryUserCampaign;
import com.omarze.entities.LotteryUserCampaignStatus;
import com.omarze.entities.Stage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * created by julian
 */
@Repository
public interface LotteryUserCampaignRepository extends JpaRepository<LotteryUserCampaign, Long> {


    Optional<LotteryUserCampaign> findByLotteryUser_IdAndCampaign_Id(Long lotteryUserId, Long campaignId);


    Page<LotteryUserCampaign> findByCampaignStatusAndCampaign_Id(LotteryUserCampaignStatus campaignStatus, Long campaignId, Pageable pageable);


    Long countByCampaignStatusAndCampaign_Id(LotteryUserCampaignStatus campaignStatus, Long campaignId);


    @Modifying
    @Query("UPDATE LotteryUserCampaign l SET l.campaignStatus = :campaignStatus WHERE l.campaign.id = :campaignId")
    void updateUserCampaignsToStatus(@Param("campaignId") Long campaignId, @Param("campaignStatus") LotteryUserCampaignStatus campaignStatus);


    @Modifying
    @Query("UPDATE LotteryUserCampaign l SET l.campaignStatus = :campaignStatus WHERE l.campaign.id = :campaignId AND l.campaignStatus <> 'LOSER'")
    void updateParticipatingUserCampaignsToStatus(@Param("campaignId") Long campaignId, @Param("campaignStatus") LotteryUserCampaignStatus campaignStatus);


    @Modifying
    @Query("UPDATE LotteryUserCampaign l SET l.campaignStatus = :campaignStatus WHERE l.campaign.id = :campaignId AND l.userNumber IN :luckyNumbers")
    void updateUserCampaignsToStatus(@Param("campaignId") Long campaignId, @Param("luckyNumbers") List<String> luckyNumbers, @Param("campaignStatus") LotteryUserCampaignStatus campaignStatus);


    @Modifying
    @Query("UPDATE LotteryUserCampaign l SET l.campaignStatus = 'LOSER' WHERE l.campaign.id = :campaignId AND l.campaignStatus = 'EVALUATING'")
    void updateUserCampaignLosers(@Param("campaignId") Long campaignId);


}
