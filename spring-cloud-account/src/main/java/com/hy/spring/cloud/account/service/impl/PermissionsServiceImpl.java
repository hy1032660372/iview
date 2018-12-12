package com.hy.spring.cloud.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hy.spring.cloud.account.domain.Entity.MenuAndPermission;
import com.hy.spring.cloud.account.domain.Entity.Permissions;
import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.domain.PageQuery;
import com.hy.spring.cloud.account.domain.SimplePage;
import com.hy.spring.cloud.account.domain.User;
import com.hy.spring.cloud.account.mapper.PermissionsServiceMapper;
import com.hy.spring.cloud.account.service.PermissionsService;
import com.hy.spring.cloud.account.util.ObjectUtil;
import com.hy.spring.cloud.account.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: PermissionsServiceImpl
 * Author:   hy103
 * Date:     2018/8/24 23:06
 * Description: permissions
 */

@Service
public class PermissionsServiceImpl implements PermissionsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PermissionsServiceMapper permissionsServiceMapper;

    @Override
    public Message getUserAuthPermissions(Principal principal) {
        logger.info("Get user auth permissions");
        User user = ObjectUtil.getUser(principal);
        //current User's permissions
        List<Permissions> permissionList = permissionsServiceMapper.getUserAuthPermissions(user.getCurrentRole().getRoleCode());

        return Message.info(permissionList);
    }

    @Override
    @Transactional
    public Message insertPermission(String menuCode, Permissions permissions) {
        logger.info("insert permission");

        MenuAndPermission menuAndPermission = new MenuAndPermission();
        menuAndPermission.setId(UUIDUtil.createUUID());
        menuAndPermission.setMenuCode(menuCode);
        menuAndPermission.setPermissionCode(permissions.getPermissionCode());
        permissionsServiceMapper.insertMenuPermission(menuAndPermission);

        permissions.setId(UUIDUtil.createUUID());
        permissionsServiceMapper.insertPermission(permissions);
        return Message.info("Success");
    }

    @Override
    public SimplePage getPermissionList(PageQuery pageQuery) {
        logger.info("get permission");
        if (pageQuery.getPage() > 0 && pageQuery.getSize() > 0) {
            PageHelper.startPage(pageQuery.getPage(), pageQuery.getSize());
        }
        Map query = pageQuery.convertFilterToMap();
        List<Permissions>  permissionList = permissionsServiceMapper.getPermissionList(query);
        return new SimplePage<Permissions>().convert(new PageInfo<>(permissionList));
    }

    @Transactional
    @Override
    public Message deletePermissionById(String menuCode, String permissionCode) {
        permissionsServiceMapper.deleteMenuPermission(menuCode,permissionCode);
        permissionsServiceMapper.deletePermission(permissionCode);
        return Message.info("记录删除成功");
    }
}
