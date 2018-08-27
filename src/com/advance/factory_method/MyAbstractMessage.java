package com.advance.factory_method;

import java.util.Map;

/**
 * @Auther: 谷天乐
 * @Date: 2018/8/27 09:14
 * @Description:
 */
public abstract class MyAbstractMessage implements IMyMessage{
    private Map<String, Object> messageParam;// 这里可以理解为生产产品所需要的原材料库。最好是个自定义的对象，这里为了不引起误解使用Map。

    @Override
    public Map<String, Object> getMessageParam() {
        return messageParam;
    }

    @Override
    public void setMessageParam(Map<String, Object> messageParam) {
        this.messageParam = messageParam;
    }
}