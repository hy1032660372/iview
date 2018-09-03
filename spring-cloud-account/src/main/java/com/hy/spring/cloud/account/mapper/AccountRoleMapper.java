package com.hy.spring.cloud.account.mapper;

import com.hy.spring.cloud.account.domain.Entity.AccountRole;
import com.hy.spring.cloud.account.util.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * FileName: AccountRoleMapper
 * Author:   hy103
 * Date:     2018/8/3 23:36
 * Description: account and role
 */

@Mapper
public interface AccountRoleMapper extends MyMapper<AccountRole> {

    void insertAccountRole(AccountRole accountRole);

}
