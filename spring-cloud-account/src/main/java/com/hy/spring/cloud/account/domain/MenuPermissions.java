package com.hy.spring.cloud.account.domain;

import com.hy.spring.cloud.account.domain.Entity.Permissions;

import java.util.List;

/**
 * FileName: MenuPermissions
 * Author:   hy103
 * Date:     2018/9/6 22:47
 * Description: relation of Menu and Permissions
 */
public class MenuPermissions {

    private String menuCode;

    private String menuName;

    private List<Permissions> permissionList;

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public List<Permissions> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permissions> permissionList) {
        this.permissionList = permissionList;
    }
}
