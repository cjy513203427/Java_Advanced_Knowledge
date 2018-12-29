package com.advance.Engine_and_Message.engine;

import com.advance.Engine_and_Message.domain.Message;
import com.advance.Engine_and_Message.factory.EngineQueueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Auther: 谷天乐
 * @Date: 2018/11/7 17:33
 * @Description:
 */
public abstract class Engine {


    protected Message message;//引擎处理对应的消息实体

    private Engine engine;//引擎对象


    public Engine(){
        this.engine = this;
    }

    public void setMessage(Message message){
        this.message = message;
    }

    public Message getMessage(){
        return this.message;
    }

    public void start(){
        new Thread(() -> {
            try{
                execute();
            }catch(Exception e){
                System.out.println("["+message.getMessageId()+"] 处理失败"+e);
            }finally{
                Long threadid=Thread.currentThread().getId();
                //线程池释放该线程
                Threadmanager.removeThreadToPool(threadid);
                //消息处理完成，回收引擎
                new EngineQueueFactory().recycle(engine);
            }
        }).start();
    }
    /**
     * @Description: 业务处理方法，所有子类重写此方法
     */
    protected abstract void execute() throws Exception;
}
