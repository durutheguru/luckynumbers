package com.omarze.services.user;


import com.omarze.entities.ApplicationUser;
import com.omarze.exception.UserNotFoundException;

import java.util.Optional;

/**
 * created by julian
 */
public interface UserDetailsProvider<T extends ApplicationUser> {


    Optional<T> findUser(String principal);

    Optional<T> findUser(String principal, String credentials);


    default T loadUserDetails(String principal) throws UserNotFoundException {
        Optional<T> userDetails = findUser(principal);
        if (!userDetails.isPresent()) {
            throw new UserNotFoundException(principal);
        }

        return userDetails.get();
    }


    String name();


}
