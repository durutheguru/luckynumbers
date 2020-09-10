package com.julianduru.omarze.config;


import com.julianduru.security.Auth;
import com.julianduru.security.entity.UserAuthId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * created by julian
 */
@Configuration
@EnableJpaAuditing(
    auditorAwareRef = "auditorProvider",
    dateTimeProviderRef = "auditorDateTimeProvider"
)
public class JpaAuditingConfiguration {


    @Bean
    public AuditorAware<UserAuthId> auditorProvider() {
        return Auth::getUserAuthId;
    }


    @Bean
    public DateTimeProvider auditorDateTimeProvider() {
        return () -> Optional.of(ZonedDateTime.now());
    }


}
