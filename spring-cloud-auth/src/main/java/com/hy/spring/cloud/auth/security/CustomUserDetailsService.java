package com.hy.spring.cloud.auth.security;

import com.hy.spring.cloud.auth.domain.User;
import com.hy.spring.cloud.auth.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author as_hy
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("username: {}", username);

        User currentUser = userMapper.checkUserLogin(username);
        if (currentUser == null) {
            throw new UsernameNotFoundException(username);
        }

        List<String> userRoleString = currentUser.getUserRole().stream().map(e->e.get("roleCode").toString()).collect(Collectors.toList());
        List<String> permissions = initPermission(userRoleString);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority grantedAuthority;
        for (String code : permissions) {
            grantedAuthority = new SimpleGrantedAuthority(code);
            authorities.add(grantedAuthority);
        }

        return new CustomUserDetails.CustomUserDetailsBuilder()
                .withUserId(currentUser.getUserId())
                .withUsername(currentUser.getUsername())
                .withPassword(currentUser.getPassword())
                .withCurrentRole(currentUser.getUserRole().get(0))
                .withRoleCode(currentUser.getUserRole())
                .withAuthorities(authorities)
                .build();
    }

    private List<String> initPermission(List<String> userRoleList) {
        List<String> permissions = userMapper.getPermissions(userRoleList);
        return permissions;
    }
}
