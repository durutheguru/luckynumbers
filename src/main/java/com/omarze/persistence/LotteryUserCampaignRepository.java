package com.omarze.persistence;


import com.omarze.entities.LotteryUserCampaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * created by julian
 */
@Repository
public interface LotteryUserCampaignRepository extends JpaRepository<LotteryUserCampaign, Long> {


    Optional<LotteryUserCampaign> findByLotteryUser_IdAndCampaign_Id(Long lotteryUserId, Long campaignId);


}
