package com.advance.Engine_and_Message.listener;

import com.advance.Engine_and_Message.factory.EngineQueueFactory;
import com.advance.Engine_and_Message.factory.MessageQueueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @Auther: 谷天乐
 * @Date: 2018/11/7 17:46
 * @Description:
 */
public class QueueMonitor {

    //监控间隔时长，单位秒
    private int monitorTimes = 10;

    public int getMonitorTimes() {
        return monitorTimes;
    }

    public void setMonitorTimes(int monitorTimes) {
        this.monitorTimes = monitorTimes;
    }

    /**
     * @Description: 队列监控初始化
     */
    public void initialize(){
        start();
    }

    private void start(){
        new Thread(){
            @Override
            public void run(){
                while(true){
                    try{
                        //只运行两次
                        Map<String, Integer> map = new MessageQueueFactory().getMessageSize();
                        StringBuffer logbuffer = new StringBuffer("\n***********************消息队列监控结果***********************\n");
                        for(Map.Entry<String, Integer> entry : map.entrySet()){
                            logbuffer.append("*消息类型["+entry.getKey()+"],待处理消息数量["+entry.getValue()+"]\n");
                        }
                        logbuffer.append("**************************************************************");
                        System.out.println(logbuffer.toString());

                        map = new EngineQueueFactory().getEngineSize();
                        logbuffer = new StringBuffer("\n***********************引擎队列监控结果***********************\n");
                        //只运行两次
                        for(Map.Entry<String, Integer> entry : map.entrySet()){
                            logbuffer.append("*引擎类型["+entry.getKey()+"],空闲引擎数量["+entry.getValue()+"]\n");
                        }
                        logbuffer.append("**************************************************************");
                        System.out.println(logbuffer.toString());
                    }catch(Exception e){
                        System.out.println(getClass().getName()+"错误");
                    }
                    try {
                        //循环等待10秒
                        Thread.sleep(1000*monitorTimes);
                    } catch (InterruptedException e) {
                        try {
                            throw new Exception("终端异常");
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }
}
