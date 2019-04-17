package com.advance.Engine_and_Message.Main;

import com.advance.Engine_and_Message.domain.EngineDomain;
import com.advance.Engine_and_Message.domain.Message;
import com.advance.Engine_and_Message.factory.EngineQueueFactory;
import com.advance.Engine_and_Message.factory.MessageQueueFactory;
import com.advance.Engine_and_Message.listener.BackReportListener;
import com.advance.Engine_and_Message.listener.CycleReportListener;
import com.advance.Engine_and_Message.queue.MessageQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 谷天乐
 * @Date: 2018/11/8 11:08
 * @Description:
 */
public class MainRun {

    static EngineDomain backrpEngineDomain = new EngineDomain();
    public static MessageQueue queue = new MessageQueue(backrpEngineDomain.getInitMessageSize());
    public static void main(String[] args) {
        /**
         * @Author 谷天乐
         * @Description 数据初始化
         * @Date 2018/11/8 15:06
         **/
        List<EngineDomain> engineDomainList = new ArrayList<>();
        //模拟一个后台报表

        backrpEngineDomain.setEngineType("后台报表");
        backrpEngineDomain.setClassName("com.advance.Engine_and_Message.engine.BackrpEngine");
        //引擎数量-1对应着线程数量
        backrpEngineDomain.setInitsize(5);
        backrpEngineDomain.setInitMessageSize(10);

        /*EngineDomain cyclerpEngineDomain = new EngineDomain();
        cyclerpEngineDomain.setEngineType("定时报表");
        cyclerpEngineDomain.setDesc("定时报表");
        cyclerpEngineDomain.setClassName("com.advance.Engine_and_Message.engine.CyclerpEngine");
        cyclerpEngineDomain.setInitsize(10);
        cyclerpEngineDomain.setInitMessageSize(15);*/
        //EngineDomain加入engineDomainList
        engineDomainList.add(backrpEngineDomain);
//        engineDomainList.add(cyclerpEngineDomain);
        //引擎队列工厂初始化
        EngineQueueFactory engineQueueFactory = new EngineQueueFactory();
        engineQueueFactory.initialize(engineDomainList);
        engineQueueFactory.getEngine(backrpEngineDomain.getEngineType());
        //消息队列工厂初始化
        MessageQueueFactory messageQueueFactory = new MessageQueueFactory();

        for(int i=0;i<backrpEngineDomain.getInitMessageSize();++i) {
            Message message = new Message();
            message.setMessageId(String.valueOf(i));
            message.setMessageSource("后台报表消息来源" + i);
            message.setMessageType("后台报表");
            message.setMessageWay("后台消息");
            message.setMessagedesc(5);

            queue.addMessage(message);
        }

        messageQueueFactory.initialize(engineDomainList,engineQueueFactory,queue);




        //后台报表扫描监听器
        BackReportListener backReportListener = new BackReportListener();
        backReportListener.start();
        //定时报表扫描监听器
//        CycleReportListener cycleReportListener = new CycleReportListener();
//        cycleReportListener.start();
    }
}