package com.oneous.webapp.security;

import com.oneous.webapp.persistance.entity.User;
import com.oneous.webapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Abdullah Al Mamun Oronno
 */
@Service("databaseAuthenticationProvider")
public class DatabaseAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private static final Logger log = LoggerFactory.getLogger(DatabaseAuthenticationProvider.class);

    @Autowired
    private UserService userService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        log.info("additionalAuthenticationChecks, userDetails={}", userDetails == null ? "null" : userDetails.toString());
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        log.info("retrieveUser, for username={}", username);

        if (StringUtils.isEmpty(username)) {
            setHideUserNotFoundExceptions(false);//Setting this will cause UsernameNotFoundExceptions to be thrown instead of BadCredentialsException
            throw new UsernameNotFoundException("Enter your username.");
        }

        User user = userService.findUserByUsername(username);

        String givenPassword = (String) authentication.getCredentials();
        if (user == null || !user.getPassword().equals(givenPassword)) {
            throw new BadCredentialsException("Incorrect username or password.");
        }

        return user;
    }
}
