package com.hy.spring.cloud.account.service.impl;

import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.domain.Entity.SysRole;
import com.hy.spring.cloud.account.domain.SysRoleImpl;
import com.hy.spring.cloud.account.mapper.AccountMapper;
import com.hy.spring.cloud.account.mapper.SysRoleMapper;
import com.hy.spring.cloud.account.service.SysRoleService;
import com.hy.spring.cloud.account.util.TreeUtil;
import com.hy.spring.cloud.account.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public SysRoleImpl getUserRoleList() {
        logger.info("load user role");
        List<SysRoleImpl> roleList = sysRoleMapper.getUserRoleList();

        //找到根节点
        SysRoleImpl sysRole = new SysRoleImpl();
        for(int i = 0; i < roleList.size(); i++){
            sysRole = roleList.get(i);
            if(sysRole.getParentCode() == null || "".equals(sysRole.getParentCode())){
                break;
            }
        }
        TreeUtil.converseToTree(roleList,sysRole);
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

    /**
     * 将树转化成List
     * @param roleList
     * @param sysRole
     */
    void converseToList(List<SysRoleImpl> roleList,SysRoleImpl sysRole){
        roleList.add(sysRole);
        SysRoleImpl current;
        List<SysRoleImpl> childrenList = sysRole.getChildren();
        for(int i = 0; i < roleList.size(); i++){
            current = roleList.get(i);
            converseToList(roleList,current);
        }
    }

}
