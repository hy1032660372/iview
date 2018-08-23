package com.hy.spring.cloud.account.service;

import com.hy.spring.cloud.account.domain.Entity.RoleAndMenu;
import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.domain.User;

import java.security.Principal;
import java.util.List;

public interface RoleMenuService {

    Message insertRoleAndMenu(List<RoleAndMenu> roleAndMenuList);

    Message getMenuByUserName(Principal principal);

}
