package com.hy.spring.cloud.account.web;

import com.hy.spring.cloud.account.domain.Entity.SysRole;
import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * FileName: RoleController
 * Author:   hy103
 * Date:     2018/8/15 22:05
 * Description: role controller
 */

@RestController
@RequestMapping("/role")
public class RoleController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * get user role list
     * @return Message
     */
    @RequestMapping(value = "getUserRoleList", method = RequestMethod.GET)
    public Message getUserRole() {
        return Message.info(sysRoleService.getUserRoleList());
    }

    /**
     * add user role
     * @param sysRole
     * @return
     */
    @RequestMapping(value = "insertUserRole", method = RequestMethod.POST)
    public Message insertUserRole(@RequestBody SysRole sysRole) {
        return sysRoleService.insertUserRole(sysRole);
    }

    /**
     * remove role
     * @param roleCode
     * @return
     */
    @RequestMapping(value = "removeRole/{roleCode}", method = RequestMethod.GET)
    public Message removeRole(@PathVariable String roleCode) {
        return sysRoleService.removeRole(roleCode);
    }

}
