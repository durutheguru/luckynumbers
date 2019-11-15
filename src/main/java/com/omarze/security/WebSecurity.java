package com.omarze.security;


import com.omarze.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * created by julian
 */
@Order(1)
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {


    private UserDetailsService userDetailsService;

    private UsernameAuthenticationProvider usernameAuthenticationProvider;

    private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    private PasswordEncoder passwordEncoder;


    public WebSecurity(UserDetailsService userDetailsService, UsernameAuthenticationProvider usernameAuthenticationProvider, UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.usernameAuthenticationProvider = usernameAuthenticationProvider;
        this.usernamePasswordAuthenticationProvider = usernamePasswordAuthenticationProvider;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().and().csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .antMatchers(Constants.API_V1_BASE + "/lottery_user/sign_up").permitAll()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
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
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());

        return source;
    }


}

