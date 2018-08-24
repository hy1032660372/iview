package com.hy.spring.cloud.account.mapper;

import com.hy.spring.cloud.account.domain.Entity.Permissions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionsServiceMapper {

    List<Permissions> getUserAuthPermissions(@Param("roleCode") String roleCode);

}
