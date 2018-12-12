package com.hy.spring.cloud.account.domain.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FileName: RoleAndMenu
 * Author:   hy103
 * Date:     2018/8/16 22:53
 * Description: role and menu
 */

@Table(name = "role_menu")
public class RoleAndMenu {

    @Id
    private String id;

    private String roleCode;

    private String menuCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
}
