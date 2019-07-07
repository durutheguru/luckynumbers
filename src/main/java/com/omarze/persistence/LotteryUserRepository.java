package com.omarze.persistence;


import com.omarze.entities.LotteryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * created by julian
 */
@Repository
public interface LotteryUserRepository extends JpaRepository<LotteryUser, Long> {


    Optional<LotteryUser> findByUsername(String username);


    Optional<LotteryUser> findByUsernameAndPassword(String username, String password);


}
