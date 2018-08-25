package com.hy.spring.cloud.account.mapper;

import com.hy.spring.cloud.account.domain.CustomMenuImpl;
import com.hy.spring.cloud.account.domain.Entity.RoleAndMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleAndMenuMapper {

    void insertRoleAndMenu(@Param("list") List<RoleAndMenu> roleAndMenuList);

    List<CustomMenuImpl> getMenuByRole(@Param("roleCode") String roleCode);
}
