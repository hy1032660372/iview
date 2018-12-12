package com.hy.spring.cloud.account.mapper;

import com.hy.spring.cloud.account.domain.Entity.Account;
import com.hy.spring.cloud.account.domain.Entity.UserLoginRecord;
import com.hy.spring.cloud.account.util.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author as_hy
 */
@Mapper
public interface AccountMapper extends MyMapper<Account> {

    Account findAccountById(@Param("id") String id);

    List<Account> findAccountByRoleCode(@Param("roleCode") String roleCode);

    void insertAccountList(@Param("accountList") List<Account> accountList);

    UserLoginRecord getLastLogin(@Param("userId") String userId);
}
