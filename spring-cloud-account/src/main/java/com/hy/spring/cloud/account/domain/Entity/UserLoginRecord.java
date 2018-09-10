package com.hy.spring.cloud.account.domain.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Table;
import java.security.Timestamp;
import java.util.Date;

/**
 * FileName: UserLoginRecord
 * Author:   hy103
 * Date:     2018/9/10 22:15
 * Description: login log
 */

@Table(name = "sys_login_record")
public class UserLoginRecord {

    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String lastLoginTime;

    private String userId;

    private String activity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
