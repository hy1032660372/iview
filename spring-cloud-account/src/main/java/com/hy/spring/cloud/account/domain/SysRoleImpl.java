package com.hy.spring.cloud.account.domain;

import com.hy.spring.cloud.account.util.TreeData;

import java.util.List;

/**
 * FileName: SysRoleImpl
 * Author:   hy103
 * Date:     2018/8/2 22:28
 * Description: Role implement
 */
public class SysRoleImpl extends TreeData {

    private String id;

    private String title;

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
}
