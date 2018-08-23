package com.hy.spring.cloud.account.domain.Entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * 用户角色
 *
 * @author as_hy
 */
@Table(name = "sys_role")
public class SysRole {

    @Id
    private String id;

    private String title;

    private String roleCode;

    private String parentCode;

    private Boolean expand;

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

    public Boolean getExpand() {
        return expand;
    }

    public void setExpand(Boolean expand) {
        this.expand = expand;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
