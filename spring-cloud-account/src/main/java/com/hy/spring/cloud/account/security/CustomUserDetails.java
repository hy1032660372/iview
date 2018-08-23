package com.hy.spring.cloud.account.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author as_hy
 */
public class CustomUserDetails implements UserDetails {
    private static final long serialVersionUID = 7673225091735750618L;
    private String userId;
    private String username;
    private String password;
    private Map currentRole;
    private List<Map> roleCode;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean enabled = true;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Map getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(Map currentRole) {
        this.currentRole = currentRole;
    }

    public List<Map> getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(List<Map> roleCode) {
        this.roleCode = roleCode;
    }

    public static class CustomUserDetailsBuilder {
        private CustomUserDetails userDetails = new CustomUserDetails();

        public CustomUserDetailsBuilder withUserId(String userId) {
            userDetails.setUserId(userId);
            return this;
        }

        public CustomUserDetailsBuilder withUsername(String username) {
            userDetails.setUsername(username);
            return this;
        }

        public CustomUserDetailsBuilder withPassword(String password) {
            userDetails.setPassword(password);
            return this;
        }

        public CustomUserDetailsBuilder withCurrentRole(Map currentRole) {
            userDetails.setCurrentRole(currentRole);
            return this;
        }

        public CustomUserDetailsBuilder withAuthorities(
                Collection<? extends GrantedAuthority> authorities) {
            userDetails.setAuthorities(authorities);
            return this;
        }

        public CustomUserDetailsBuilder withEnabled(boolean enabled) {
            userDetails.setEnabled(enabled);
            return this;
        }

        public CustomUserDetailsBuilder withRoleCode(List<Map> roleCode){
            userDetails.setRoleCode(roleCode);
            return this;
        }

        public UserDetails build() {
            return userDetails;
        }
    }
}
