package com.hy.spring.cloud.account.mapper;

import com.hy.spring.cloud.account.domain.Entity.SysRole;
import com.hy.spring.cloud.account.domain.SysRoleImpl;
import com.hy.spring.cloud.account.util.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMapper extends MyMapper<SysRole> {

    List<SysRoleImpl> getUserRoleList();

    void insertRole(SysRole sysRole);

    void removeRole(@Param("roleCode") String roleCode);

    List<SysRoleImpl> getRoleChildren(@Param("roleCode") String roleCode);

}
