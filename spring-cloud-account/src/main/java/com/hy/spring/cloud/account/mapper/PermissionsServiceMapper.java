package com.hy.spring.cloud.account.mapper;

import com.hy.spring.cloud.account.domain.Entity.Permissions;
import com.hy.spring.cloud.account.util.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PermissionsServiceMapper extends MyMapper<Permissions> {

    List<Permissions> getUserAuthPermissions(@Param("roleCode") String roleCode);

    void insertPermission(Permissions permissions);

    List<Permissions> getPermissionList(Map query);
}
