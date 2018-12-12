package com.hy.spring.cloud.message.domain.Entity;

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

    private Message(Integer messageCode, String message){
        this.messageCode = messageCode;
        this.message = message;
    }

    public Message(Integer messageCode, String level, String message){
        this.message = message;
        this.messageCode = messageCode;
        this.level = level;
    }

    public Message(Integer messageCode, String level, Object data, String message){
        this.data = data;
        this.messageCode = messageCode;
        this.level = level;
        this.message = message;
    }

    public static Message success(String message) {
        return new Message(0, "success", message);
    }

    public static Message success(Object data) {
        return new Message(0, "success", data,"success");
    }

    public static Message info(String message) {
        return new Message(1, "normal", message);
    }

    public static Message info(Object data) {
        return new Message(1, "normal", data,"ok");
    }

    public static Message warn(String message) {
        return new Message(2, "warning", message);
    }

    public static Message warn(Object data) {
        return new Message(2, "warning");
    }

    public static Message error(String message) {
        return new Message(3, "error", message);
    }

    public static Message error(Object data) {
        return new Message(3, "error");
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
