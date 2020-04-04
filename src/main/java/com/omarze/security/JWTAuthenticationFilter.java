package com.omarze.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarze.Constants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.Assert;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * created by julian
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final static String JWT_AUTH_CLAIM = "authorizations";


    private ObjectMapper objectMapper = new ObjectMapper();


    private final AuthenticationManager authenticationManager;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserLogin userLogin = objectMapper.readValue(request.getInputStream(), UserLogin.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword())
            );
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        List<String> authorities = extractAuthorities(authResult);

        String token = JWT.create()
                .withSubject(authResult.getPrincipal().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + Constants.Security.EXPIRATION_TIME))
                .withArrayClaim(JWT_AUTH_CLAIM, authorities.toArray(new String[authorities.size()]))
                .sign(Algorithm.HMAC512(Constants.Security.SECRET.getBytes()));
        
        response.addHeader(Constants.Security.HEADER_STRING, Constants.Security.TOKEN_PREFIX + token);
    }


    private List<String> extractAuthorities(Authentication authResult) {
        Assert.notNull(authResult, "Authentication should not be null");

        return authResult.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());
    }


}
