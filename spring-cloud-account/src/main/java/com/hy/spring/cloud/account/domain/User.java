package com.hy.spring.cloud.account.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hy.spring.cloud.account.domain.Entity.SysRole;
import com.hy.spring.cloud.account.util.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;

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

        Collection<LinkedHashMap> roleCode;

        try{
            this.currentRole = (SysRole)ObjectUtil.mapToObjectIntrospector((Map)userMap.get("currentRole"),SysRole.class);
            LinkedHashMap linkedMap = (LinkedHashMap)userMap.get("roleCode");
            roleCode = (Collection<LinkedHashMap>)linkedMap.values();
            roleCode.stream().forEach(m->{
                try {
                    this.sysCodeList.add((SysRole)ObjectUtil.mapToObjectIntrospector(m,SysRole.class));
                }catch (Exception r){
                    r.getMessage();
                }
            });

            LinkedHashMap authList = (LinkedHashMap)userMap.get("authorities");
            authList.values().stream().forEach(m->{
                Map<String,String> n = (Map)m;
                this.authorities.add(n.get("authority"));
            });
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        this.userId = userMap.get("userId").toString();
        this.userName = userMap.get("username").toString();
        this.enabled = userMap.get("enabled").equals("true")?true:false;
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
