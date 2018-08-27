package com.advance.factory_method;

public interface IMyMessageFactory {
    public IMyMessage createMessage(String messageType);
}