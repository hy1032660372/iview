package com.hy.spring.cloud.account.domain;

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

    private String code;

    private String parentCode;

    private String expand;

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

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
