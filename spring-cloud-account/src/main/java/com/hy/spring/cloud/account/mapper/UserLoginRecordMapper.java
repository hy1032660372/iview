package com.hy.spring.cloud.account.mapper;

import com.hy.spring.cloud.account.domain.Entity.UserLoginRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserLoginRecordMapper {

    void insertRecord(UserLoginRecord userLoginRecord);

    List<UserLoginRecord> readRecord(String userId);

    UserLoginRecord LastRecord(String userId);
}
