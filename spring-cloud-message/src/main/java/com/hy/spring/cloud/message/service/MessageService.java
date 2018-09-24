package com.hy.spring.cloud.message.service;

import com.hy.spring.cloud.message.domain.Entity.MessageBody;

public interface MessageService {

    void insertMessage(MessageBody messageBody);

}
