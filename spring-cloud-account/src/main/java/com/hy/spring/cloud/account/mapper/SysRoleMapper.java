package com.hy.spring.cloud.account.mapper;

import com.hy.spring.cloud.account.domain.SysRole;
import com.hy.spring.cloud.account.domain.SysRoleImpl;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper {

    List<SysRoleImpl> getUserRoleList();

    void insertUserRole(SysRole sysRole);
}
