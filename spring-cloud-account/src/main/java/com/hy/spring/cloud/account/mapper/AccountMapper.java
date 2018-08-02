package com.hy.spring.cloud.account.mapper;

import com.hy.spring.cloud.account.domain.Account;
import com.hy.spring.cloud.account.util.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author NiBo
 */
@Mapper
public interface AccountMapper extends MyMapper<Account> {

    Account findAccountById(@Param("id") String id);

    List<Account> findAccountByRoleId(@Param("roleId") String roleId);

    void insertAccountList(@Param("accountList") List<Account> accountList);
}
