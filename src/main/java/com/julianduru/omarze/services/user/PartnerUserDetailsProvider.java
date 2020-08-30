package com.julianduru.omarze.services.user;


import com.julianduru.Constants;
import com.julianduru.omarze.entities.PartnerUser;
import com.julianduru.omarze.persistence.PartnerUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * created by julian
 */
@Component
public class PartnerUserDetailsProvider implements UserDetailsProvider<PartnerUser> {


    private final PartnerUserRepository userRepository;


    private final PasswordEncoder passwordEncoder;


    public PartnerUserDetailsProvider(PartnerUserRepository partnerRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = partnerRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Boolean hasUser(String principal) {
        return userRepository.existsByUsername(principal);
    }


    @Override
    public Boolean hasEmail(String email) {
        return userRepository.existsByEmail(email);
    }


    @Override
    public Optional<PartnerUser> findUser(String principal) {
        return userRepository.findByUsername(principal);
    }


    @Override
    public Optional<PartnerUser> findUser(String principal, String credentials) {
        Optional<PartnerUser> partnerUser = userRepository.findByUsername(principal);
        if (partnerUser.isPresent() && passwordEncoder.matches(credentials, partnerUser.get().getPassword())) {
            return partnerUser;
        }

        return Optional.empty();
    }


    @Override
    public String name() {
        return Constants.Users.PARTNER_USER;
    }


}
