package com.omarze.config;


import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * created by julian
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Value(value = "${lottery.auth.apiAudience}")
    private String apiAudience;


    @Value(value = "${lottery.auth.issuer}")
    private String issuer;


    public void configure(HttpSecurity httpSecurity) throws Exception {
        JwtWebSecurityConfigurer
            .forRS256(apiAudience, issuer)
            .configure(httpSecurity)
            .cors().and().csrf().disable().authorizeRequests()
            .anyRequest().permitAll();
    }


}
