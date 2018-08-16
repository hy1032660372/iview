package com.hy.spring.cloud.account.web;

import com.hy.spring.cloud.account.domain.Entity.RoleAndMenu;
import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.service.RoleMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private RoleMenuService sysRoleService;

    public Message insertRoleAndMenu(@RequestBody List<RoleAndMenu> roleAndMenuList ){
        return sysRoleService.insertRoleAndMenu(roleAndMenuList);
    }

}
