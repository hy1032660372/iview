package com.hy.spring.cloud.account.web.api;

import com.hy.spring.cloud.account.domain.Entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NiBo
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountApiController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PreAuthorize("hasAuthority('USER_QUERY') or #oauth2.hasScope('server')")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Account getAccountById(@PathVariable String id) {
        logger.info("id: {}", id);
        logger.info("{}", getUserDetails());
        return new Account(id, "Tom", "aaaa", 20);
    }

    private Map<String, Object> getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        HashMap details = (HashMap) oAuth2Authentication.getUserAuthentication().getDetails();
        return details;
    }
}
