package com.hy.spring.cloud.account.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hy.spring.cloud.account.domain.Entity.SysRole;
import com.hy.spring.cloud.account.util.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author as_hy
 */
public class User implements Serializable {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final long serialVersionUID = 5765721034684112921L;

    //user id
    private String userId;
    private String userName;
    private SysRole currentRole;
    private List<SysRole> sysCodeList = new ArrayList<>();
    private List<String> authorities = new ArrayList<>();
    private Boolean enabled;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String lastLogin;

    public User(Map userMap){
        try{
            this.currentRole = (SysRole)ObjectUtil.mapToObjectIntrospector((Map)userMap.get("currentRole"),SysRole.class);
            List<Map> roleMapList = (List<Map>)userMap.get("roleCode");
            for(Map map:roleMapList){
                this.sysCodeList.add((SysRole)ObjectUtil.mapToObjectIntrospector(map,SysRole.class));
            }

            List<Map> authList = (List<Map>)userMap.get("authorities");
            for(Map map:authList){
                this.authorities.add(map.get("authority").toString());
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        this.userId = userMap.get("userId").toString();
        this.userName = userMap.get("username").toString();
        this.enabled = (Boolean)userMap.get("enabled");
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public SysRole getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(SysRole currentRole) {
        this.currentRole = currentRole;
    }

    public List<SysRole> getSysCodeList() {
        return sysCodeList;
    }

    public void setSysCodeList(List<SysRole> sysCodeList) {
        this.sysCodeList = sysCodeList;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


}
