package com.hy.spring.cloud.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hy.spring.cloud.account.domain.Entity.Account;
import com.hy.spring.cloud.account.domain.Entity.AccountRole;
import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.domain.PageQuery;
import com.hy.spring.cloud.account.domain.SimplePage;
import com.hy.spring.cloud.account.domain.User;
import com.hy.spring.cloud.account.mapper.AccountMapper;
import com.hy.spring.cloud.account.service.AccountService;
import com.hy.spring.cloud.account.util.ObjectUtil;
import com.hy.spring.cloud.account.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

/**
 * @author as_hy
 */
@Service
public class AccountServiceImpl implements AccountService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account findAccountById(String id) {
        logger.info("22222");
        return accountMapper.findAccountById(id);
    }

    @Override
    public SimplePage<Account> pageAccount(PageQuery pageQuery) {
        if (pageQuery.getPage() > 0 && pageQuery.getSize() > 0) {
            PageHelper.startPage(pageQuery.getPage(), pageQuery.getSize());
        }
        List<Account> accounts = accountMapper.findAccountByRoleCode(pageQuery.getFilter());
        return new SimplePage<Account>().convert(new PageInfo<>(accounts));
    }

    @Transactional
    @Override
    public Message saveAccount(Account account,String roleCode) {
        account.setId(UUIDUtil.createUUID());
        account.setPassword(new BCryptPasswordEncoder().encode("123456"));
        accountMapper.insertSelective(account);
        return Message.info("Success");
    }

    @Transactional
    @Override
    public int deleteAccount(String id) {
        return accountMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    @Override
    public int updateAccount(Account account) {
        return accountMapper.updateByPrimaryKeySelective(account);
    }

    @Override
    public Message getCurrentAccount(Principal principal) {
        User user = ObjectUtil.getUser(principal);
        String dataStr = accountMapper.getLastLogin(user.getUserId())==null?"":accountMapper.getLastLogin(user.getUserId()).getOperateTime();
        user.setLastLogin(dataStr);
        return Message.info(user);
    }
}
