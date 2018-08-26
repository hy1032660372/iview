package com.hy.spring.cloud.account.service;

import com.hy.spring.cloud.account.domain.Entity.Permissions;
import com.hy.spring.cloud.account.domain.Message;

import java.security.Principal;

public interface PermissionsService {

    Message getUserAuthPermissions(Principal principal);

    Message insertPermission(Permissions permissions);
}
