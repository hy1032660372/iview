package com.hy.spring.cloud.account.service.impl;

import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.domain.Entity.SysRole;
import com.hy.spring.cloud.account.domain.SysRoleImpl;
import com.hy.spring.cloud.account.domain.User;
import com.hy.spring.cloud.account.mapper.AccountMapper;
import com.hy.spring.cloud.account.mapper.SysRoleMapper;
import com.hy.spring.cloud.account.service.SysRoleService;
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

@Service
public class SysRoleServiceImpl implements SysRoleService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public SysRoleImpl getUserRoleList(Principal principal) {
        logger.info("load user role");
        List<SysRoleImpl> roleList = sysRoleMapper.getUserRoleList();
        SysRoleImpl sysRole = ObjectUtil.getRoleTree(principal,roleList);
        return sysRole;
    }

    @Override
    public Message insertUserRole(SysRole sysRole) {
        logger.info("insert user role");
        sysRole.setId(UUIDUtil.createUUID());
        sysRoleMapper.insertRole(sysRole);
        return Message.info("Success");
    }

    @Override
    public Message removeRole(String roleCode) {
        logger.info("remove user role");
        if(sysRoleMapper.getRoleChildren(roleCode).size() == 0
                && accountMapper.findAccountByRoleCode(roleCode).size() == 0){
            sysRoleMapper.removeRole(roleCode);
            return Message.info("Success");
        }else{
            return Message.info("False");
        }
    }

    @Override
    public Message getUserAuthRole(Principal principal) {
        List<SysRoleImpl> list = new ArrayList<>();
        List<SysRoleImpl> roleList = sysRoleMapper.getUserRoleList();
        SysRoleImpl sysRole = ObjectUtil.getRoleTree(principal,roleList);
        TreeUtil.converseToList(list,sysRole);
        return Message.info(list);
    }

    @Override
    public Message getRoleCurrent(Principal principal,String menuCode) {
        User user = ObjectUtil.getUser(principal);
        List<SysRole> sysRoleList = sysRoleMapper.getRoleCurrent(menuCode);
        return Message.info(sysRoleList);
    }
}
