package com.hy.spring.cloud.auth.mapper;

import com.hy.spring.cloud.auth.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author as_hy
 */
@Mapper
public interface UserMapper{

    User checkUserLogin(@Param("username") String username);

    List<String> getPermissions(@Param("userRoleList") List<String> userRoleList);
}
