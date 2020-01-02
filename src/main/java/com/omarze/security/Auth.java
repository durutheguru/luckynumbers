package com.omarze.security;


import com.omarze.entities.UserAuthId;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * created by julian
 */
public class Auth {


    public static Authentication getContext() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


    public static void setContext(Authentication auth) {
        SecurityContextHolder.getContext().setAuthentication(auth);
    }


    public static Optional<UserAuthId> getUserAuthId() {
        Authentication authentication = getContext();
        if (authentication == null) {
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();

        String user = principal instanceof User ? ((User)principal).getUsername() : principal.toString();
        final String[] roleId = {""};

        authentication.getAuthorities()
                .stream()
                .findFirst()
                .ifPresent(a -> roleId[0] = a.getAuthority());

        UserAuthId authId = new UserAuthId(user, roleId[0]);
        return Optional.of(authId);
    }


    public static UserAuthId getUserAuthId(boolean errorIfMissing) throws NoSuchElementException {
        Optional<UserAuthId> authId = getUserAuthId();
        if (!authId.isPresent()) {
            if (errorIfMissing) {
                throw new NoSuchElementException("User details could not be found in Security Context");
            }
            else {
                return null;
            }
        }

        return authId.get();
    }


}

