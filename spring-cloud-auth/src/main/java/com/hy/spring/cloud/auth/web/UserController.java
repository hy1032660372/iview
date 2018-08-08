package com.hy.spring.cloud.auth.web;

import com.hy.spring.cloud.auth.domain.Message;
import com.hy.spring.cloud.auth.security.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author as_hy
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @RequestMapping(value = "current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        logger.info("获取用户信息");
        return principal;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public Message logout(HttpServletRequest request) {
        return Message.info(customUserDetailsService.logout(request));
    }

}
