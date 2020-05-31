package com.omarze.services.user;


import com.omarze.Constants;
import com.omarze.entities.BackOfficeUser;
import com.omarze.persistence.BackOfficeUserRepository;
import com.omarze.security.Auth;
import com.omarze.security.AuthContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * created by julian
 */
@Component
@RequiredArgsConstructor
public class BackOfficeUserDetailsProvider implements UserDetailsProvider<BackOfficeUser> {


    private final BackOfficeUserRepository userRepository;


    private final PasswordEncoder passwordEncoder;



    @Override
    public Boolean hasUser(String principal) {
        return userRepository.existsByUsername(principal);
    }


    @Override
    public Boolean hasEmail(String email) {
        return userRepository.existsByEmail(email);
    }


    @Override
    public Optional<BackOfficeUser> findUser(String principal) {
        Auth.setContext(AuthContext.backOfficeUser());
        return userRepository.findByUsername(principal);
    }


    @Override
    public Optional<BackOfficeUser> findUser(String principal, String credentials) {
        Optional<BackOfficeUser> user = findUser(principal);
        if (user.isPresent() && passwordEncoder.matches(credentials, user.get().getPassword())){
            return user;
        }

        return Optional.empty();
    }


    @Override
    public String name() {
        return Constants.Users.BACK_OFFICE_USER;
    }


}
