package com.omarze.config;


import com.omarze.entities.UserAuthId;
import com.omarze.security.Auth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

/**
 * created by julian
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {


    @Bean
    public AuditorAware<UserAuthId> auditorProvider() {
        return () -> {
            Authentication authentication = Auth.getContext();
            if (authentication == null) {
                return Optional.empty();
            }

            String user = ((User)authentication.getPrincipal()).getUsername();
            final String[] roleId = {""};

            authentication.getAuthorities()
                    .stream()
                    .findFirst()
                    .ifPresent(a -> roleId[0] = a.getAuthority());

            UserAuthId authId = new UserAuthId(user, roleId[0]);

            return Optional.of(authId);
        };
    }


}
