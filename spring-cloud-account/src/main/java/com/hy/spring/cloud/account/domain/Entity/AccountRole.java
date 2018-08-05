package com.hy.spring.cloud.account.domain.Entity;

import javax.persistence.Table;

/**
 * FileName: AccountRole
 * Author:   hy103
 * Date:     2018/8/3 23:29
 * Description: account and role relationship
 */

@Table(name = "account_role")
public class AccountRole {

    private String id;

    private String accountId;

    private String roleCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
