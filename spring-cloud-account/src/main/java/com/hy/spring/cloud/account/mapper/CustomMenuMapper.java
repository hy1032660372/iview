package com.hy.spring.cloud.account.mapper;

import com.hy.spring.cloud.account.domain.CustomMenuImpl;
import com.hy.spring.cloud.account.domain.Entity.Account;
import com.hy.spring.cloud.account.domain.Entity.CustomMenu;
import com.hy.spring.cloud.account.util.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomMenuMapper extends MyMapper<Account> {

    void removeMenu(@Param("menuCode") String menuCode);

    void insertMenu(CustomMenu customMenu);
}
