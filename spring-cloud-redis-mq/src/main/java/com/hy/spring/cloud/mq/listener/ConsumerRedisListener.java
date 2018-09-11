package com.hy.spring.cloud.mq.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * FileName: ConsumerRedisListener
 * Author:   hy103
 * Date:     2018/9/11 22:29
 * Description: topic MessageListener
 */


public class ConsumerRedisListener implements MessageListener {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ConsumerRedisListener(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public ConsumerRedisListener() {
        this.stringRedisTemplate = new StringRedisTemplate();
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        doBusiness(message);
    }

    /**
     * 打印 message body 内容
     * @param message
     */
    public void doBusiness(Message message) {
        Object value = stringRedisTemplate.getValueSerializer().deserialize(message.getBody());
        System.out.println("consumer message: " + String.valueOf(value));
    }

}
