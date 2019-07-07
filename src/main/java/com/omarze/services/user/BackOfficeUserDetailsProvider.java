package com.omarze.services.user;


import com.omarze.Constants;
import com.omarze.entities.BackOfficeUser;
import com.omarze.persistence.BackOfficeUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * created by julian
 */
@Component
public class BackOfficeUserDetailsProvider implements UserDetailsProvider<BackOfficeUser> {


    private final BackOfficeUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public BackOfficeUserDetailsProvider(BackOfficeUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Optional<BackOfficeUser> findUser(String principal) {
        return userRepository.findByUsername(principal);
    }

    @Override
    public Optional<BackOfficeUser> findUser(String principal, String credentials) {
        Optional<BackOfficeUser> user = userRepository.findByUsername(principal);
        if (user.isPresent() && passwordEncoder.matches(credentials, user.get().getPassword())){
            return user;
        }

        return Optional.empty();
    }


    @Override
    public String name() {
        return Constants.Users.BACKOFFICE_USER;
    }


}
