package com.hy.spring.cloud.account.service.impl;

import com.hy.spring.cloud.account.domain.Entity.RoleAndMenu;
import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.mapper.CustomMenuMapper;
import com.hy.spring.cloud.account.mapper.RoleAndMenuMapper;
import com.hy.spring.cloud.account.service.RoleMenuService;
import com.hy.spring.cloud.account.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * FileName: RoleMenuServiceImpl
 * Author:   hy103
 * Date:     2018/8/16 22:47
 * Description: role and menu
 */

@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RoleAndMenuMapper roleAndMenuMapper;

    @Override
    public Message insertRoleAndMenu(List<RoleAndMenu> roleAndMenuList) {
        roleAndMenuList.stream().forEach(e->{e.setId(UUIDUtil.createUUID());});
        roleAndMenuMapper.insertRoleAndMenu(roleAndMenuList);
        return Message.info("Success");
    }
}
