package com.hy.spring.cloud.mq;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Junit 测试套件
 *
 * @author as_hy
 */

public class JunitTestSuite {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedisStringOps() {
        stringRedisTemplate.convertAndSend("string-topic","hello world");

    }

}
