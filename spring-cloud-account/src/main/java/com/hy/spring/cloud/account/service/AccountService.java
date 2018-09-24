package com.hy.spring.cloud.account.service;

import com.hy.spring.cloud.account.domain.*;
import com.hy.spring.cloud.account.domain.Entity.Account;
import com.hy.spring.cloud.account.domain.Message;

import java.security.Principal;
import java.util.List;

/**
 * @author as_hy
 */
public interface AccountService {

    Account findAccountById(String id);

    SimplePage<Account> pageAccount(PageQuery query);

    Message saveAccount(Account account, String roleCode);

    int deleteAccount(String id);

    int updateAccount(Account account);

    Message getCurrentAccount(Principal principal);
}
