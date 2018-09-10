package com.hy.spring.cloud.account.service.impl;

import com.hy.spring.cloud.account.domain.Entity.UserLoginRecord;
import com.hy.spring.cloud.account.mapper.UserLoginRecordMapper;
import com.hy.spring.cloud.account.service.UserLoginRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: UserLoginRecordServiceImpl
 * Author:   hy103
 * Date:     2018/9/10 22:38
 * Description: Login Record
 */

@Service
public class UserLoginRecordServiceImpl implements UserLoginRecordService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserLoginRecordMapper userLoginRecordMapper;

    @Override
    public void insertRecord(UserLoginRecord userLoginRecord) {
        logger.info("insert log");
        userLoginRecordMapper.insertRecord(userLoginRecord);
    }

    @Override
    public List<UserLoginRecord> readRecord(String userId) {
        logger.info("read log list");
        return userLoginRecordMapper.readRecord(userId);
    }

    @Override
    public UserLoginRecord LastRecord(String userId) {
        logger.info("read log");
        return userLoginRecordMapper.LastRecord(userId);
    }
}
