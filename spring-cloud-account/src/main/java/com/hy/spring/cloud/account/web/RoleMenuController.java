package com.hy.spring.cloud.account.web;

import com.hy.spring.cloud.account.domain.Entity.RoleAndMenu;
import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.domain.User;
import com.hy.spring.cloud.account.service.RoleMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: RoleMenuController
 * Author:   hy103
 * Date:     2018/8/16 22:45
 * Description: role and menu
 */

@RestController
@RequestMapping("/roleAndMenu")
public class RoleMenuController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RoleMenuService roleMenuService;

    public Message insertRoleAndMenu(@RequestBody List<RoleAndMenu> roleAndMenuList ){
        return roleMenuService.insertRoleAndMenu(roleAndMenuList);
    }

    /**
     * get user role list
     * @return Message
     */
    @RequestMapping(value = "getMenuByRole", method = RequestMethod.GET)
    public Message getMenuByUserName(Principal principal) {
        return roleMenuService.getMenuByUserName(principal);
    }
}
