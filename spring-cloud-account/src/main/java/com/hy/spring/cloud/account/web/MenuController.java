package com.hy.spring.cloud.account.web;

import com.hy.spring.cloud.account.domain.Entity.CustomMenu;
import com.hy.spring.cloud.account.domain.Message;
import com.hy.spring.cloud.account.service.CustomMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * FileName: MenuController
 * Author:   hy103
 * Date:     2018/8/15 22:03
 * Description: menu controller
 */

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private CustomMenuService customMenuService;

    /**
     * remove menu
     * @param menuCode
     * @return
     */
    @RequestMapping(value = "removeMenu/{menuCode}", method = RequestMethod.GET)
    public Message removeRole(@PathVariable String menuCode) {
        return customMenuService.removeMenu(menuCode);
    }

    /**
     * add customMenu
     * @param customMenu
     * @return
     */
    @RequestMapping(value = "insertMenu", method = RequestMethod.POST)
    public Message insertMenu(@RequestBody CustomMenu customMenu) {
        return customMenuService.insertMenu(customMenu);
    }
}
