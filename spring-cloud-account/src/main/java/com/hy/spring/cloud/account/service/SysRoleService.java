package com.hy.spring.cloud.account.service;

import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.domain.Entity.SysRole;
import com.hy.spring.cloud.account.domain.SysRoleImpl;

public interface SysRoleService {

    SysRoleImpl getUserRoleList();

    Message insertUserRole(SysRole sysRole);

    Message removeRole(String roleCode);
}
