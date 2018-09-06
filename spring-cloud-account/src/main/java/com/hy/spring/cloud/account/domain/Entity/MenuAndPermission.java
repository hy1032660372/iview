package com.hy.spring.cloud.account.domain.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FileName: MenuAndPermission
 * Author:   hy103
 * Date:     2018/9/6 21:09
 * Description: menu and permission
 */

@Table(name = "menu_permission")
public class MenuAndPermission {

    @Id
    private String id;

    private String menuCode;

    private String permissionCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }
}
