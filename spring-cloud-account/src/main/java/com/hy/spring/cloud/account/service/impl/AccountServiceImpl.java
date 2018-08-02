package com.hy.spring.cloud.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hy.spring.cloud.account.domain.Account;
import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.domain.PageQuery;
import com.hy.spring.cloud.account.domain.SimplePage;
import com.hy.spring.cloud.account.mapper.AccountMapper;
import com.hy.spring.cloud.account.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author NiBo
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
        List<Account> accounts = accountMapper.findAccountByRoleId(pageQuery.getFilter());
        return new SimplePage<Account>().convert(new PageInfo<>(accounts));
    }

    @Transactional
    @Override
    public int saveAccount(Account account) {
        return accountMapper.insertSelective(account);
    }

    @Transactional
    @Override
    public int deleteAccount(String id) {
        return accountMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int batchDeleteAccount(List<String> ids) {
        return 0;
    }

    @Transactional
    @Override
    public int updateAccount(Account account) {
        return accountMapper.updateByPrimaryKeySelective(account);
    }

    @Transactional
    @Override
    public Message insertAccountList(List<Account> accountList) {
        accountMapper.insertAccountList(accountList);
        return Message.info("保存成功");
    }
}
