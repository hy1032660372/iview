package com.hy.spring.cloud.account.service.impl;

import com.hy.spring.cloud.account.domain.Entity.AccountRole;
import com.hy.spring.cloud.account.mapper.AccountRoleMapper;
import com.hy.spring.cloud.account.service.AccountRoleService;
import com.hy.spring.cloud.account.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FileName: AccountRoleServiceImpl
 * Author:   hy103
 * Date:     2018/8/3 23:34
 * Description: account and role relationship
 */

@Service
public class AccountRoleServiceImpl implements AccountRoleService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountRoleMapper accountRoleMapper;

    public void insertAccountRole(String accountId,String roleCode){
        AccountRole accountRole = new AccountRole();
        accountRole.setId(UUIDUtil.createUUID());
        accountRole.setAccountId(accountId);
        accountRole.setRoleCode(roleCode);
        accountRoleMapper.insertAccountRole(accountRole);
    }

}
