package com.hy.spring.cloud.account.service;

import com.hy.spring.cloud.account.domain.Entity.UserLoginRecord;

import java.util.List;

public interface UserLoginRecordService {

    void insertRecord(UserLoginRecord userLoginRecord);

    List<UserLoginRecord> readRecord(String userId);

    UserLoginRecord LastRecord(String userId);

}
