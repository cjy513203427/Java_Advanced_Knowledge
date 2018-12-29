package com.advance.Engine_and_Message.listener;

import com.advance.Engine_and_Message.domain.Message;
import com.advance.Engine_and_Message.engine.Engine;
import com.advance.Engine_and_Message.factory.EngineQueueFactory;
import com.advance.Engine_and_Message.queue.MessageQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: 谷天乐
 * @Date: 2018/11/7 17:46
 * @Description:
 * 监控消息
 */
public class MessageListener {


    private MessageQueue messageQueue;

    private String messageType;

    private EngineQueueFactory engineQueueFactory;

    public void setMessageQueue(MessageQueue messageQueue){
        this.messageQueue = messageQueue;
    }

    public void setMessageType(String messageType){
        this.messageType = messageType;
    }

    public EngineQueueFactory getEngineQueueFactory() {
        return engineQueueFactory;
    }

    public void setEngineQueueFactory(EngineQueueFactory engineQueueFactory) {
        this.engineQueueFactory = engineQueueFactory;
    }

    public void start(){
        new Thread(() -> {
            while(true){
                try{
                    Message message = messageQueue.getMessage();
                    if(message != null){
                        //获取指定消息类型的引擎
                        Engine engine = engineQueueFactory.getEngine(messageType);//?
                        if(engine != null){
                            //给引擎设置消息
                            engine.setMessage(message);
                            engine.start();

                        }else{
                            //引擎为空则加入消息队列
                            messageQueue.addMessage(message);//
                        }
                    }
                    Thread.sleep(1000);
                }catch(Exception e){
                    System.out.println("["+messageType+"] 消息处理异常!"+e);
                }
            }
        }).start();
    }
}
