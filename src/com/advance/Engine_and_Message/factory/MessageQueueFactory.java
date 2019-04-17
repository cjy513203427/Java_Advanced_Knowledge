package com.advance.Engine_and_Message.factory;

import com.advance.Engine_and_Message.domain.EngineDomain;
import com.advance.Engine_and_Message.domain.Message;
import com.advance.Engine_and_Message.listener.MessageListener;
import com.advance.Engine_and_Message.queue.MessageQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: 谷天乐
 * @Date: 2018/11/7 17:43
 * @Description:
 */
public class MessageQueueFactory {


    //private EngineQueueFactory engineQueueFactory;

    //引擎Map，key为消息类型，每种类型的消息一个队列
    private ConcurrentHashMap<String, MessageQueue> queueMap = new ConcurrentHashMap<String, MessageQueue>();

    private static MessageQueueFactory instance = null;

    /**
     * @Description: 单例模式初始化队列工厂
     * @return
     */
    public static MessageQueueFactory getInstance(){
        if(instance == null)
            instance = new MessageQueueFactory();
        return instance;
    }

    public void initialize(List<EngineDomain> engineList,EngineQueueFactory engineQueueFactory,MessageQueue queue){
        int counter = 0;
        //engineListSize为2
        for(EngineDomain domain : engineList){
            this.queueMap.put(domain.getEngineType(), queue);
            MessageListener listener = new MessageListener();
            listener.setMessageQueue(queue);
            listener.setMessageType(domain.getEngineType());
            //给消息队列工厂设置引擎队列工厂
            listener.setEngineQueueFactory(engineQueueFactory);
            listener.start();
            counter ++;
        }
        System.out.println("成功初始化["+counter+"]个消息队列");
    }

    /**
     * @Description: 新增消息，将消息按类型入队列
     * @param message
     */
    @SuppressWarnings("rawtypes")
    public boolean addMessage(Message message){
        MessageQueue queue = queueMap.get(message.getMessageType());
        if(queue == null){
            System.out.println("["+message.getMessageType()+"] 消息类型["+message.getMessageType()+"]对应的消息队列获取失败，系统中没有注册此类消息！");
            return false;
        }
        queue.addMessage(message);
        return true;
    }

    /**
     * @Description: 获取消息队列中各种类型消息的数量
     * @return
     */
    public Map<String, Integer> getMessageSize(){
        Map<String, Integer> messages = new HashMap<String, Integer>();
        for(Map.Entry<String, MessageQueue> entry : queueMap.entrySet()){
            messages.put(entry.getKey(), entry.getValue().getMessageSize());
        }
        return messages;
    }
}
