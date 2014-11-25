package com.oneous.webapp.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Abdullah Al Mamun Oronno
 *         Created on 8/27/14
 */
@Service("authenticationFailureHandler")
public class SecurityAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private static final Logger log = LoggerFactory.getLogger(SecurityAuthenticationFailureHandler.class);

    @Autowired
    private UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter;


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        String userName = request.getParameter(usernamePasswordAuthenticationFilter.getUsernameParameter());

        log.info("onAuthenticationFailure- username={}, exceptionClass={}", userName, exception.getClass().getName());

        String parameter = "unknown";
        if (exception instanceof UsernameNotFoundException) {
            parameter = "usernameEmpty";
        } else if (exception instanceof BadCredentialsException) {
            parameter = "badCredential";
        } else if (exception instanceof LockedException) {
            parameter = "userLocked";
        }

        response.sendRedirect("login?error=" + parameter);
    }
}
