package com.advance.Engine_and_Message.queue;

import com.advance.Engine_and_Message.engine.Engine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Auther: 谷天乐
 * @Date: 2018/11/7 17:48
 * @Description:
 */
public class EngineQueue {


    private BlockingQueue<Engine> engineQueue;

    public EngineQueue(int initsize){
        engineQueue = new ArrayBlockingQueue<Engine>(initsize);
    }

    /**
     * @Description: 初始化时增加引擎
     * @param engine
     * @return
     */
    public boolean addEngine(Engine engine){
        return engineQueue.add(engine);
    }

    /**
     * @Description: 引擎回收
     * @param engine
     */
    public  void recycle(Engine engine){
        try {
            engine.setMessage(null);
            //引擎入队
            engineQueue.put(engine);
        } catch (InterruptedException e) {
            System.out.println("["+engine.getMessage().getMessageId()+"] 引擎回收失败！"+ e);
        }
    }

    /**
     * @Description: 请求引擎，当系统有消息需要处理里，需要获取引擎
     * @return
     */
    public Engine getEngine(){
        try {
            //引擎出队
            return engineQueue.take();
        } catch (InterruptedException e) {
            System.out.println("请求引擎失败！"+e);
        }
        return null;
    }

    /**
     * @Description: 返回引擎队列空闲引擎数量
     * @return
     */
    public int getEngineSize(){
        return engineQueue.size();
    }
}
