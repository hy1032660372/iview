package com.hy.spring.cloud.account.service;

import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.domain.SysRole;

import java.util.List;

public interface SysRoleService {

    SysRole getUserRoleList();

    Message insertUserRole(SysRole sysRole);
}
