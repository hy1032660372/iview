package com.hy.spring.cloud.mq.config;

import com.hy.spring.cloud.mq.listener.ConsumerRedisListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * FileName: PublisherConfig
 * Author:   hy103
 * Date:     2018/9/11 22:10
 * Description: publIsh config
 */

@Configuration
public class RedisConfig {

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public ConsumerRedisListener consumerRedis() {
        return new ConsumerRedisListener();
    }

    @Bean
    public ChannelTopic topic() {
        return new ChannelTopic("string-topic");
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory);

        container.addMessageListener(consumerRedis(),topic());
        return container;

    }
}
