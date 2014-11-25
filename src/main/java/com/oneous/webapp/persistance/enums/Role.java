package com.oneous.webapp.persistance.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Abdullah Al Mamun Oronno
 */
public enum Role implements GrantedAuthority {

    ROLE_ADMIN,
    ROLE_USER;

    //This GrantedAuthority interface implementation is to support SpringSecurity's role management cleanly
    @Override
    public String getAuthority() {
        return name();
    }
}
