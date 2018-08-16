package com.hy.spring.cloud.account.mapper;

import com.hy.spring.cloud.account.domain.Entity.RoleAndMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleAndMenuMapper {

    void insertRoleAndMenu(List<RoleAndMenu> roleAndMenuList);
    
}
