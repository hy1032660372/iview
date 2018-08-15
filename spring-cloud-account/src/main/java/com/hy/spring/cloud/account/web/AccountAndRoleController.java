package com.hy.spring.cloud.account.web;

import com.hy.spring.cloud.account.domain.Entity.Account;
import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.service.AccountRoleService;
import com.hy.spring.cloud.account.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * FileName: AccountAndRoleController
 * Author:   hy103
 * Date:     2018/8/15 22:07
 * Description: Account and role controller
 */

@RestController
@RequestMapping("/AccountAndRole")
public class AccountAndRoleController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountRoleService accountRoleService;

    @Autowired
    private AccountService accountService;

    /**
     * add new account
     * @param account
     * @return Message
     */
    @RequestMapping(value = "insertAccount/{roleCode}", method = RequestMethod.POST)
    public Message insertUser(@PathVariable String roleCode, @RequestBody Account account) {
        accountService.saveAccount(account,roleCode);
        accountRoleService.insertAccountRole(account.getId(),roleCode);
        return Message.info("Success");
    }

}
