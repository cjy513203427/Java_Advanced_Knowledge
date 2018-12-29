package com.advance.Engine_and_Message.queue;

import com.advance.Engine_and_Message.domain.Message;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 谷天乐
 * @Date: 2018/11/7 17:48
 * @Description:
 */
public class MessageQueue {


    private BlockingQueue<Message> messageQueue;

    public MessageQueue() {
        // TODO Auto-generated constructor stub
    }

    public MessageQueue(int initsize){
        messageQueue = new ArrayBlockingQueue(initsize);
    }

    /**
     * @Description: 新增消息，如果消息入消息队列超过2秒则直接返回
     * @param message
     * @return
     */
    public boolean addMessage(Message message){
        try {
            //消息入队
            messageQueue.offer(message, 2, TimeUnit.SECONDS);
            System.out.println("["+message.getMessageId()+"] 消息入队列");
        } catch (InterruptedException e) {
            System.out.println("["+message.getMessageId()+"] 消息入队列失败.消息内容["+ JSON.toJSONString(message)+"]" + e);
            return false;
        }
        return true;
    }

    /**
     * @Description: 从消息队列中读取消息
     * @return
     */
    public Message getMessage(){
        try {
            //消息出队
            return messageQueue.take();
        } catch (InterruptedException e) {
            System.out.println("读取消息失败!"+e);
        }
        return null;
    }

    /**
     * @Description: 获取消息队列中消息数量
     * @return
     */
    public int getMessageSize(){
        return messageQueue.size();
    }
}
