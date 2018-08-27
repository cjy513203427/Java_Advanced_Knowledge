package com.advance.factory_method;

/**
 * @Auther: 谷天乐
 * @Date: 2018/8/27 09:17
 * @Description:
 */
public class MyMessageSms extends MyAbstractMessage {
    @Override
    public void sendMesage() throws Exception {
        // TODO Auto-generated method stub
        if (null == getMessageParam()
                || null == getMessageParam().get("PHONENUM")
                || "".equals(getMessageParam().get("PHONENUM"))) {
            throw new Exception("发送短信,需要传入PHONENUM参数");// 为了简单起见异常也不自定义了
        }// 另外短信信息，以及其他各种协议参数等等都要处理

        System.out.println("我是短信，发送通知给" + getMessageParam().get("PHONENUM"));
    }
}