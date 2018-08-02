package com.hy.spring.cloud.account.domain;

import java.util.List;

/**
 * FileName: SysRoleImpl
 * Author:   hy103
 * Date:     2018/8/2 22:28
 * Description: Role implement
 */
public class SysRoleImpl extends SysRole{

    private List<SysRoleImpl> children;

    public List<SysRoleImpl> getChildren() {
        return children;
    }

    public void setChildren(List<SysRoleImpl> children) {
        this.children = children;
    }
}
