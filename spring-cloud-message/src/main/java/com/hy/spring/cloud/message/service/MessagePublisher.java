package com.hy.spring.cloud.message.service;

public interface MessagePublisher {

    boolean sendMessage(String message);

}
