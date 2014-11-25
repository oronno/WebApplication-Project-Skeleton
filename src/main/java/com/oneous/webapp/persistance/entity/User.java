package com.oneous.webapp.persistance.entity;

import com.oneous.webapp.persistance.enums.Role;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * @author Abdullah Al Mamun Oronno (www.oneous.com)
 */

@Entity
public class User extends Persistent implements UserDetails {

    @Size(min = 6, max = 100)
    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Size(min = 5, max = 50)
    @Pattern(regexp = "^[a-z0-9]*$", message = "Only small letters and numbers allowed")
    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Size(min = 6, max = 50)
    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false)
    private boolean locked;

    @NotEmpty
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    private Collection<Role> roles;

    /**
     * Getters and Setters *
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    /**
     * Spring Security UserDetails required methods *
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked();
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //Not implemented
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //Not implemented
    }

}
