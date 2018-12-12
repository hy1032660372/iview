package com.hy.spring.cloud.account.domain;

import com.hy.spring.cloud.account.util.TreeData;

/**
 * FileName: RolePermissions
 * Author:   hy103
 * Date:     2018/8/15 21:36
 * Description: role permissions
 */
public class CustomMenuImpl extends TreeData {

    private String id;

    private String title;

    private String menuUrl;

    private Boolean expand;

    private Boolean checked;

    private String icon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Boolean getExpand() {
        return expand;
    }

    public void setExpand(Boolean expand) {
        this.expand = expand;
    }

    public String getIcon() {
        return icon;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
