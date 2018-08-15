package com.hy.spring.cloud.account.service;

import com.hy.spring.cloud.account.domain.CustomMenuImpl;
import com.hy.spring.cloud.account.domain.Entity.CustomMenu;
import com.hy.spring.cloud.account.domain.Message;

public interface CustomMenuService {

    CustomMenuImpl getCustomMenu();

    Message removeMenu(String menuCode);

    Message insertMenu(CustomMenu customMenu);
}
