package com.hy.spring.cloud.mq.service;

import com.hy.spring.cloud.mq.domain.Message;
import com.hy.spring.cloud.mq.domain.Wish;

import java.util.List;
import java.util.Map;

/**
 * @author as_hy
 */
public interface MQService {

    String queryWishList(Map query);

    String pubMsg(String id);
}
