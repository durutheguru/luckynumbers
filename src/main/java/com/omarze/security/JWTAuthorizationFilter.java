package com.omarze.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.google.common.base.Strings;
import com.omarze.Constants;
import com.omarze.util.AppLogger;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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


    private UserDetailsService userDetailsService;


    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }


    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String headerToken = request.getHeader(Constants.Security.HEADER_STRING);

        if (Strings.isNullOrEmpty(headerToken) || !headerToken.startsWith(Constants.Security.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            UsernamePasswordAuthenticationToken authToken = getAuthenticationToken(headerToken);
            Auth.setContext(authToken);

            chain.doFilter(request, response);
        }
        catch (TokenExpiredException e) {
            AppLogger.error(e);
            response.sendError(HttpStatus.FORBIDDEN.value(), "Token has expired");
        }
    }


    public UsernamePasswordAuthenticationToken getAuthenticationToken(String headerToken) throws TokenExpiredException {
        String user = JWT.require(Algorithm.HMAC512(Constants.Security.SECRET))
                .build()
                .verify(headerToken.replace(Constants.Security.TOKEN_PREFIX, ""))
                .getSubject();

        if (Strings.isNullOrEmpty(user)) {
            return null;
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(user);

        if (userDetails == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }


}


