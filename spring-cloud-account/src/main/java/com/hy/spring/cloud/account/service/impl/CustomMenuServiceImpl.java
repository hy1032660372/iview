package com.hy.spring.cloud.account.service.impl;

import com.hy.spring.cloud.account.domain.CustomMenuImpl;
import com.hy.spring.cloud.account.domain.Entity.CustomMenu;
import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.domain.User;
import com.hy.spring.cloud.account.mapper.CustomMenuMapper;
import com.hy.spring.cloud.account.service.CustomMenuService;
import com.hy.spring.cloud.account.util.ObjectUtil;
import com.hy.spring.cloud.account.util.TreeUtil;
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
 * Date:     2018/8/15 21:12
 * Description: permissions impl
 */

@Service
public class CustomMenuServiceImpl implements CustomMenuService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CustomMenuMapper customMenuMapper;

    @Override
    public Message removeMenu(String menuCode) {
        customMenuMapper.removeMenu(menuCode);
        return Message.info("Success");
    }

    @Override
    public Message insertMenu(CustomMenu customMenu) {
        customMenu.setId(UUIDUtil.createUUID());
        customMenuMapper.insertMenu(customMenu);
        return Message.info("Success");
    }
}
