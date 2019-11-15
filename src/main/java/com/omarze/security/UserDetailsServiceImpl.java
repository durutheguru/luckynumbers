package com.omarze.security;


import com.omarze.entities.ApplicationUser;
import com.omarze.exception.NoUserProviderFoundException;
import com.omarze.exception.ServiceException;
import com.omarze.exception.UserNotFoundException;
import com.omarze.services.user.UserDetailsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Optional;

/**
 * created by julian
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);


    final List<UserDetailsProvider<? extends ApplicationUser>> userDetailsProviders;


    public UserDetailsServiceImpl(List<UserDetailsProvider<? extends ApplicationUser>> userDetailsProviders) {
        this.userDetailsProviders = userDetailsProviders;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUserByUsername(username, true);
    }


    public UserDetails loadUser(String username, String credentials) {
        return loadUserFromProviders(username, credentials);
    }


    public UserDetails loadUserByUsername(String username, boolean errorIfNotFound) throws UsernameNotFoundException {
        try {
            verifyState();
            return loadUserDetailsFromProviders(username);
        }
        catch (UsernameNotFoundException | ServiceException e) {
            if (errorIfNotFound) {
                throw new UsernameNotFoundException(e.getMessage(), e);
            }
            else {
                return null;
            }
        }
    }


    private UserDetails loadUserDetailsFromProviders(String username) throws UsernameNotFoundException {
        User user;
        ApplicationUser applicationUser;

        for (UserDetailsProvider provider : userDetailsProviders) {
            try {
                applicationUser = provider.loadUserDetails(username);
                user = applicationUser.toUser();

                return user;
            }
            catch (UserNotFoundException e) {
                logger.warn(String.format("User %s was not found as %s", username, provider.name()));
                // fail silently if user was not found in the provider
            }
        }

        throw new UsernameNotFoundException(String.format("%s was not found", username ));
    }


    private UserDetails loadUserFromProviders(String username, String credentials) throws UsernameNotFoundException {
        User user;
        Optional<? extends ApplicationUser> applicationUser;

        for (UserDetailsProvider<? extends ApplicationUser> provider : userDetailsProviders) {
            applicationUser = provider.findUser(username, credentials);

            if (applicationUser == null || !applicationUser.isPresent()) {
                continue;
            }

            user = applicationUser.get().toUser();
            return user;
        }

        return null;
    }


    private void verifyState() throws ServiceException {
        if (userDetailsProviders == null || userDetailsProviders.isEmpty()) {
            throw new NoUserProviderFoundException();
        }
    }


}
