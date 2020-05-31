package com.omarze.security;


import com.omarze.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * created by julian
 */
@Order(1)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {


    private final UserDetailsService userDetailsService;

    private final UsernameAuthenticationProvider usernameAuthenticationProvider;

    private final UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    private final PasswordEncoder passwordEncoder;

    private final CommonsRequestLoggingFilter loggingFilter;



    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers(Constants.API_BASE + "/lottery_user/sign_up").permitAll()
            .antMatchers(Constants.API_BASE + "/partner").permitAll()
            .antMatchers(
                "/api/**"
            ).fullyAuthenticated()
            .and()
            .addFilterBefore(loggingFilter, JWTAuthenticationFilter.class)
            .addFilter(new JWTAuthenticationFilter(authenticationManager()))
            .addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetailsService))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    @Override
    public void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder
            .authenticationProvider(usernamePasswordAuthenticationProvider)
            .authenticationProvider(usernameAuthenticationProvider)
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }


}

