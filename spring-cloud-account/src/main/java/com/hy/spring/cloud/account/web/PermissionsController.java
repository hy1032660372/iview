package com.hy.spring.cloud.account.web;

import com.hy.spring.cloud.account.domain.Entity.CustomMenu;
import com.hy.spring.cloud.account.domain.Entity.Permissions;
import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.service.PermissionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * FileName: PermissionsController
 * Author:   hy103
 * Date:     2018/8/24 22:42
 * Description: permissions
 */

@RestController
@RequestMapping("/permissions")
public class PermissionsController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PermissionsService permissionsService;

    /**
     * get user role list
     * @return Message
     */
    @RequestMapping(value = "getUserAuthPermissions", method = RequestMethod.GET)
    public Message getUserAuthPermissions(Principal principal) {
        return permissionsService.getUserAuthPermissions(principal);
    }

    /**
     * add permission
     * @param permissions
     * @return
     */
    @RequestMapping(value = "insertPermission", method = RequestMethod.POST)
    public Message insertPermission(@RequestBody Permissions permissions) {
        return permissionsService.insertPermission(permissions);
    }
}
