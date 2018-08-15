package com.hy.spring.cloud.account.domain.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FileName: CustomPermissions
 * Author:   hy103
 * Date:     2018/8/15 21:26
 * Description: getCustomPermissions entity
 */

@Table(name = "sys_custommenu")
public class CustomMenu {

    @Id
    private String id;

    private String title;

    private String code;

    private String userRole;

    private String menuUrl;

    private Boolean expand;

    private String parentCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Boolean getExpand() {
        return expand;
    }

    public void setExpand(Boolean expand) {
        this.expand = expand;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
