package com.hy.spring.cloud.message.service.impl;

import com.hy.spring.cloud.message.domain.Entity.MessageBody;
import com.hy.spring.cloud.message.mapper.MessageMapper;
import com.hy.spring.cloud.message.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FileName: MessageServiceImpl
 * Author:   hy103
 * Date:     2018/8/3 23:34
 * Description: Message ServiceImpl
 */

@Service
public class MessageServiceImpl implements MessageService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MessageMapper messageMapper;

    public void insertMessage(MessageBody t){
        messageMapper.select(t);
    }
}
