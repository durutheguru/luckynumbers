package com.omarze.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.common.base.Strings;
import com.omarze.Constants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created by julian
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {


    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String headerToken = request.getHeader(Constants.Security.HEADER_STRING);

        if (Strings.isNullOrEmpty(headerToken) || !headerToken.startsWith(Constants.Security.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authToken = getAuthenticationToken(headerToken);
        SecurityContextHolder.getContext().setAuthentication(authToken);

        chain.doFilter(request, response);
    }


    public UsernamePasswordAuthenticationToken getAuthenticationToken(String headerToken) {
        if (Strings.isNullOrEmpty(headerToken)) {
            return null;
        }

        String user = JWT.require(Algorithm.HMAC512(Constants.Security.SECRET))
                .build()
                .verify(headerToken.replace(Constants.Security.TOKEN_PREFIX, ""))
                .getSubject();

        if (Strings.isNullOrEmpty(user)) {
            return null;
        }

        return new UsernameAuthenticationToken(user);
    }



}
