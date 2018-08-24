package com.hy.spring.cloud.account.service;

import com.hy.spring.cloud.account.domain.Message;

import java.security.Principal;

public interface PermissionsService {

    Message getUserAuthPermissions(Principal principal);
}
