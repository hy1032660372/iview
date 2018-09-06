package com.hy.spring.cloud.account.service;

import com.hy.spring.cloud.account.domain.Entity.Permissions;
import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.domain.PageQuery;
import com.hy.spring.cloud.account.domain.SimplePage;

import java.security.Principal;

public interface PermissionsService {

    Message getUserAuthPermissions(Principal principal);

    Message insertPermission(String menuCode, Permissions permissions);

    SimplePage getPermissionList(PageQuery pageQuery);

    Message deletePermissionById(String menuCode, String permissionCode);
}
