package com.hy.spring.cloud.account.service;

import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.domain.Entity.SysRole;
import com.hy.spring.cloud.account.domain.SysRoleImpl;

import java.security.Principal;

public interface SysRoleService {

    SysRoleImpl getUserRoleList(Principal principal);

    Message insertUserRole(SysRole sysRole);

    Message removeRole(String roleCode);

    Message getUserAuthRole(Principal principal);
}
