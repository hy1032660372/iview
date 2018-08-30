package com.hy.spring.cloud.account.web;

import com.hy.spring.cloud.account.domain.Entity.Permissions;
import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.domain.PageQuery;
import com.hy.spring.cloud.account.domain.SimplePage;
import com.hy.spring.cloud.account.service.PermissionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * get user role list
     * @return Message
     */
    @RequestMapping(value = "getPermissionList", method = RequestMethod.GET)
    public SimplePage getPermissionList(PageQuery pageQuery) {
        return permissionsService.getPermissionList(pageQuery);
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

    /**
     * delete account by Id
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Message deletePermissionById(@PathVariable String id) {
        int result = permissionsService.deletePermissionById(id);
        if (result <= 0) {
            return Message.warn("要删除的记录不存在或已经被删除");
        }
        return Message.info("记录删除成功");
    }
}
