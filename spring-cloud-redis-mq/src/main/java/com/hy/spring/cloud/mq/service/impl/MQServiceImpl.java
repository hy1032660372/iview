package com.hy.spring.cloud.mq.service.impl;

import com.hy.spring.cloud.mq.mapper.MQMapper;
import com.hy.spring.cloud.mq.service.MQService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author as_hy
 */
@Service
@Transactional
public class MQServiceImpl implements MQService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MQMapper mqMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public String queryWishList(Map query){
        return "aaa";
    }

    @Override
    public String pubMsg(String id) {
        if ("1".equals(id)){
            stringRedisTemplate.convertAndSend("phone","18888888888");
            logger.info("Publisher sendes Topic... ");
            return "success";
        }else {
            return "no my phone";
        }
    }

}
