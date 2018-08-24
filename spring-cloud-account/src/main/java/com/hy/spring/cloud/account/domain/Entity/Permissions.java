package com.hy.spring.cloud.account.domain.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FileName: Permissions
 * Author:   hy103
 * Date:     2018/8/24 23:16
 * Description: permissions entity
 */

@Table(name = "sys_permission")
public class Permissions {

    @Id
    private String id;

    private String permissionName;

    private String permissionCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }
}
