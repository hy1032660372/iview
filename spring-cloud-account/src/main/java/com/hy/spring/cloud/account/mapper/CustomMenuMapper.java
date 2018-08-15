package com.hy.spring.cloud.account.mapper;

import com.hy.spring.cloud.account.domain.CustomMenuImpl;
import com.hy.spring.cloud.account.domain.Entity.CustomMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomMenuMapper {

    List<CustomMenuImpl> getCustomMenuList();

    void removeMenu(@Param("menuCode") String menuCode);

    void insertMenu(CustomMenu customMenu);
}
