package com.hy.spring.cloud.account.service.impl;

import com.hy.spring.cloud.account.domain.CustomMenuImpl;
import com.hy.spring.cloud.account.domain.Entity.RoleAndMenu;
import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.domain.User;
import com.hy.spring.cloud.account.mapper.RoleAndMenuMapper;
import com.hy.spring.cloud.account.service.RoleMenuService;
import com.hy.spring.cloud.account.util.TreeUtil;
import com.hy.spring.cloud.account.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Message getMenuByUserName(Principal principal) {
        OAuth2Authentication oa = ((OAuth2Authentication) principal);
        HashMap userInfo = (HashMap)oa.getUserAuthentication().getDetails();
        User user = new User((Map)userInfo.get("principal"));
        List<CustomMenuImpl> menuList = roleAndMenuMapper.getMenuByRole(user.getCurrentRole().getRoleCode());

        //找到根节点
        CustomMenuImpl customMenu = new CustomMenuImpl();
        for(int i = 0; i < menuList.size(); i++){
            customMenu = menuList.get(i);
            if(user.getCurrentRole().getRoleCode().equals(customMenu.getParentCode())){
                break;
            }
        }
        TreeUtil.converseToTree(menuList,customMenu);
        return Message.info(customMenu);
    }
}
