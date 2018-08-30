package com.hy.spring.cloud.account.service.impl;

import com.hy.spring.cloud.account.domain.CustomMenuImpl;
import com.hy.spring.cloud.account.domain.Entity.RoleAndMenu;
import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.domain.User;
import com.hy.spring.cloud.account.mapper.RoleAndMenuMapper;
import com.hy.spring.cloud.account.service.RoleMenuService;
import com.hy.spring.cloud.account.util.ObjectUtil;
import com.hy.spring.cloud.account.util.TreeUtil;
import com.hy.spring.cloud.account.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
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
        roleAndMenuMapper.removeRoleAndMenu(roleAndMenuList.get(0).getRoleCode());
        roleAndMenuList.stream().forEach(e->{e.setId(UUIDUtil.createUUID());});
        roleAndMenuMapper.insertRoleAndMenu(roleAndMenuList);
        return Message.info("Success");
    }

    @Override
    public Message getTreeMenuByCurrentRole(Principal principal) {
        User user = ObjectUtil.getUser(principal);
        return getMenuByRole(user.getCurrentRole().getRoleCode());
    }

    @Override
    public Message getMenuByCurrentRole(Principal principal,String roleCode) {
        User user = ObjectUtil.getUser(principal);
        List<CustomMenuImpl> allMenuList = new ArrayList<>();
        List<CustomMenuImpl> roleMenuList = new ArrayList<>();

        //Get All Menu
        Message allMenu = getMenuByRole(user.getCurrentRole().getRoleCode());
        CustomMenuImpl allMenuImpl = (CustomMenuImpl)allMenu.getData();
        TreeUtil.converseToList(allMenuList,allMenuImpl);

        //Get Role Menu
        Message roleMenu = getMenuByRole(roleCode);
        CustomMenuImpl roleMenuImpl = (CustomMenuImpl)roleMenu.getData();
        TreeUtil.converseToList(roleMenuList,roleMenuImpl);

        for(CustomMenuImpl am:allMenuList){
            am.setChecked(false);
            am.setChildren(new ArrayList());
            if(!"root-menu".equals(am.getParentCode()) && !"root-menu".equals(am.getCode())){
                for(CustomMenuImpl rm:roleMenuList){
                    if(am.getCode().equals(rm.getCode()) && !"root-menu".equals(rm.getParentCode())){
                        am.setChecked(true);
                        break;
                    }
                }
            }
        }

        //找到根节点
        CustomMenuImpl customMenu = new CustomMenuImpl();
        for(int i = 0; i < allMenuList.size(); i++){
            customMenu = allMenuList.get(i);
            if(customMenu.getCode().equals("root-menu")){
                break;
            }
        }

        TreeUtil.converseToTree(allMenuList,customMenu);
        return Message.info(customMenu);
    }

    @Override
    public Message getMenuByRole(String roleCode) {
        logger.info("Get Menu by role");
        List<CustomMenuImpl> menuList = roleAndMenuMapper.getMenuByRole(roleCode);
        //找到根节点
        CustomMenuImpl customMenu = new CustomMenuImpl();
        for(int i = 0; i < menuList.size(); i++){
            customMenu = menuList.get(i);
            if(customMenu.getCode().equals("root-menu")){
                customMenu.setExpand(true);
                break;
            }
        }
        TreeUtil.converseToTree(menuList,customMenu);
        return Message.info(customMenu);
    }
}
