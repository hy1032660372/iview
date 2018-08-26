package com.hy.spring.cloud.account.service.impl;

import com.hy.spring.cloud.account.domain.Entity.Permissions;
import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.domain.User;
import com.hy.spring.cloud.account.mapper.PermissionsServiceMapper;
import com.hy.spring.cloud.account.service.PermissionsService;
import com.hy.spring.cloud.account.util.ObjectUtil;
import com.hy.spring.cloud.account.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

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
        List<Permissions> permissionList = permissionsServiceMapper.getUserAuthPermissions(user.getCurrentRole().getRoleCode());
        return Message.info(permissionList);
    }

    @Override
    public Message insertPermission(Permissions permissions) {
        logger.info("insert permission");
        permissions.setId(UUIDUtil.createUUID());
        permissionsServiceMapper.insertPermission(permissions);
        return Message.info("Success");
    }
}
