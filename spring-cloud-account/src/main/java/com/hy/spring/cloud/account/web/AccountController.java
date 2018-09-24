package com.hy.spring.cloud.account.web;

import com.hy.spring.cloud.account.domain.Entity.Account;
import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.domain.PageQuery;
import com.hy.spring.cloud.account.domain.User;
import com.hy.spring.cloud.account.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author as_hy
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public Message pageQueryAccount(PageQuery query) {
        return Message.info(accountService.pageAccount(query));
    }

    /**
     * Get account by Id
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Message getAccountById(@PathVariable String id) {
        logger.info("Hello world！");
        Account account = accountService.findAccountById(id);
        logger.info("account: {}",account.getId());
        return Message.info(account);
    }

    /**
     * delete account by Id
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('USER_DELETE')")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Message deleteAccountById(@PathVariable String id) {
        int result = accountService.deleteAccount(id);
        if (result <= 0) {
            return Message.warn("要删除的记录不存在或已经被删除");
        }
        return Message.info("记录删除成功");
    }

    @PreAuthorize("hasAuthority('USER_UPDATE')")
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Message updateAccount(@PathVariable String id, @RequestBody Account account) {
        account.setId(id);
        int result = accountService.updateAccount(account);
        if (result <= 0) {
            return Message.warn("更新记录信息已不存在");
        }

        return Message.info("记录更新成功");
    }

    @RequestMapping(value = "current", method = RequestMethod.GET)
    public Message getCurrentAccount(Principal principal) {
        logger.debug("name: {}", principal.getName());
        return accountService.getCurrentAccount(principal);
    }

    @RequestMapping(value = "message/{message}", method = RequestMethod.GET)
    public ResponseEntity sayHello(@PathVariable String message) {
//        logger.info("{}", getUserDetails());
        return ResponseEntity.ok("say: " + message);
    }

    @PreAuthorize("hasAuthority('USER_QUERY')")
    @RequestMapping(value = "message1/{message}", method = RequestMethod.POST)
    public ResponseEntity sayHello2(@PathVariable String message) {
//        logger.info("{}", getUserDetails());
        return ResponseEntity.ok("POST say: " + message);
    }


    @RequestMapping(value = "message/{message}", method = RequestMethod.POST)
    public ResponseEntity sayHello3(@PathVariable String message,String userRole) {
//        logger.info("{}", getUserDetails());
        return ResponseEntity.ok("POST say: " + message);
    }

    private Map<String, Object> getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        HashMap details = (HashMap) oAuth2Authentication.getUserAuthentication().getDetails();
        return details;
    }

}
