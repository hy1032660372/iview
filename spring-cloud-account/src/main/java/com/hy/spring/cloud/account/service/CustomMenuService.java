package com.hy.spring.cloud.account.service;

import com.hy.spring.cloud.account.domain.Entity.CustomMenu;
import com.hy.spring.cloud.account.domain.Message;

public interface CustomMenuService {

    Message removeMenu(String menuCode);

    Message insertMenu(CustomMenu customMenu);
}
