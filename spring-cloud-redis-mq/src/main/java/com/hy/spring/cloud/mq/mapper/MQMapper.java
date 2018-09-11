package com.hy.spring.cloud.mq.mapper;

import com.hy.spring.cloud.mq.util.MyMapper;
import com.hy.spring.cloud.mq.domain.Wish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author as_hy
 */
@Mapper
public interface MQMapper extends MyMapper<Wish> {


}
