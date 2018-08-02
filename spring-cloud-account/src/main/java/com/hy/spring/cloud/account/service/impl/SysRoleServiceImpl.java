package com.hy.spring.cloud.account.service.impl;

import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.domain.SysRole;
import com.hy.spring.cloud.account.domain.SysRoleImpl;
import com.hy.spring.cloud.account.mapper.SysRoleMapper;
import com.hy.spring.cloud.account.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysRole getUserRoleList() {
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
        converseToTree(roleList,sysRole);
        return sysRole;
    }

    @Override
    public Message insertUserRole(SysRole sysRole) {
        logger.info("insert user role");
        sysRoleMapper.insertUserRole(sysRole);
        return Message.info("Success");
    }

    /**
     * 生成树
     * @param roleList
     * @param sysRole
     */
    void converseToTree(List<SysRoleImpl> roleList, SysRoleImpl sysRole){
        List<SysRoleImpl> childrenNodeList = new ArrayList<>();
        SysRoleImpl currentNode;
        for(int i = 0; i < roleList.size(); i++){
            currentNode = roleList.get(i);
            if(sysRole.getCode().equals(currentNode.getParentCode())){
                childrenNodeList.add(currentNode);
                converseToTree(roleList,currentNode);
            }
        }
        sysRole.setChildren(childrenNodeList);
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
