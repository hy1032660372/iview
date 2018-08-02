package com.hy.spring.cloud.wish.domain;

public class Message {

    private Object data;
    private String message;
    private String level;
    private Integer messageCode;

    public Message() {
    }

    private Message(String message){
        this.message = message;
    }

    private Message(Integer messageCode,String message){
        this.messageCode = messageCode;
        this.message = message;
    }

    public Message(Integer messageCode, String level,String message){
        this.message = message;
        this.messageCode = messageCode;
        this.level = level;
    }

    public Message(Integer messageCode, String level,Object data){
        this.data = data;
        this.messageCode = messageCode;
        this.level = level;
    }

    public static Message info(String message) {
        return new Message(0, "普通消息", message);
    }

    public static Message info(Object data) {
        return new Message(0, "普通消息", data);
    }

    public static Message warn(String message) {
        return new Message(1, "警告消息", message);
    }

    public static Message warn(Object data) {
        return new Message(1, "警告消息");
    }

    public static Message error(String message) {
        return new Message(2, "错误消息", message);
    }

    public static Message error(Object data) {
        return new Message(2, "错误消息");
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(Integer messageCode) {
        this.messageCode = messageCode;
    }
}
