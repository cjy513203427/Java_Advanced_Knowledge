package com.advance.Engine_and_Message.domain;

/**
 * @Auther: 谷天乐
 * @Date: 2018/11/7 17:33
 * @Description:
 * 消息类
 */
public class Message<T> {
    private String messageId;//消息标识，对应为消息实例的唯一标识
    private String messageType;//消息类型，对应消息处理服务
    private String messageWay;//消息方式，同步/异步消息
    private T object;//消息体，即指定的消息对象
    private Object messageSource;//消息来源，记得消息来源，同步消息时根据来源返回结果

    public String getMessageId() {
        return messageId;
    }
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    public String getMessageType() {
        return messageType;
    }
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
    public String getMessageWay() {
        return messageWay;
    }
    public void setMessageWay(String messageWay) {
        this.messageWay = messageWay;
    }
    public T getObject() {
        return object;
    }
    public void setObject(T object) {
        this.object = object;
    }
    public Object getMessageSource() {
        return messageSource;
    }
    public void setMessageSource(Object messageSource) {
        this.messageSource = messageSource;
    }
}
