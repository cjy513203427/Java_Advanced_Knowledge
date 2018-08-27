package com.advance.factory_method;

import java.util.Map;

/**
 * @Auther: 谷天乐
 * @Date: 2018/8/27 09:13
 * @Description:
 */
public interface IMyMessage {
    public Map<String, Object> getMessageParam();

    public void setMessageParam(Map<String, Object> messageParam);

    public void sendMesage() throws Exception;// 发送通知/消息
}