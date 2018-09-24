package com.hy.spring.cloud.account.mapper;

import com.hy.spring.cloud.account.domain.Entity.CustomMenu;
import com.hy.spring.cloud.account.util.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CustomMenuMapper extends MyMapper<CustomMenu> {

    void removeMenu(@Param("menuCode") String menuCode);

    void insertMenu(CustomMenu customMenu);
}
