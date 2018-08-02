package com.hy.spring.cloud.account.service;

import com.hy.spring.cloud.account.domain.*;

import java.util.List;

/**
 * @author as_hy
 */
public interface AccountService {

    Account findAccountById(String id);

    SimplePage<Account> pageAccount(PageQuery query);

    int saveAccount(Account account);

    int deleteAccount(String id);

    int batchDeleteAccount(List<String> ids);

    int updateAccount(Account account);

    Message insertAccountList(List<Account> accountList);
}
