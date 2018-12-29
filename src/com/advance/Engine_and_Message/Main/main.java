package com.advance.Engine_and_Message.Main;

import com.advance.Engine_and_Message.domain.EngineDomain;
import com.advance.Engine_and_Message.domain.Message;
import com.advance.Engine_and_Message.engine.BackrpEngine;
import com.advance.Engine_and_Message.engine.Engine;
import com.advance.Engine_and_Message.factory.EngineQueueFactory;
import com.advance.Engine_and_Message.factory.MessageQueueFactory;
import com.advance.Engine_and_Message.listener.BackReportListener;
import com.advance.Engine_and_Message.listener.CycleReportListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 谷天乐
 * @Date: 2018/11/8 11:08
 * @Description:
 */
public class main {
    public static void main(String[] args) {
        /**
         * @Author 谷天乐
         * @Description 数据初始化
         * @Date 2018/11/8 15:06
         **/
        List<EngineDomain> engineDomainList = new ArrayList<>();
        //模拟一个后台报表和定时报表
        EngineDomain backrpEngineDomain = new EngineDomain();
        backrpEngineDomain.setEngineType("后台报表");
        backrpEngineDomain.setDesc("后台报表");
        backrpEngineDomain.setClassName("com.advance.Engine_and_Message.engine.BackrpEngine");
        backrpEngineDomain.setInitsize(30);
        backrpEngineDomain.setInitMessageSize(100);

        EngineDomain cyclerpEngineDomain = new EngineDomain();
        cyclerpEngineDomain.setEngineType("定时报表");
        cyclerpEngineDomain.setDesc("定时报表");
        cyclerpEngineDomain.setClassName("com.advance.Engine_and_Message.engine.CyclerpEngine");
        cyclerpEngineDomain.setInitsize(50);
        cyclerpEngineDomain.setInitMessageSize(100);
        //EngineDomain加入engineDomainList
        engineDomainList.add(backrpEngineDomain);
        engineDomainList.add(cyclerpEngineDomain);
        //引擎队列工厂初始化
        EngineQueueFactory engineQueueFactory = new EngineQueueFactory();
        engineQueueFactory.getEngine(backrpEngineDomain.getEngineType());
        engineQueueFactory.initialize(engineDomainList);
        //消息队列工厂初始化
        MessageQueueFactory messageQueueFactory = new MessageQueueFactory();
        messageQueueFactory.initialize(engineDomainList,engineQueueFactory);

        //后台报表扫描监听器
        BackReportListener backReportListener = new BackReportListener();
        backReportListener.start();
        //定时报表扫描监听器
        CycleReportListener cycleReportListener = new CycleReportListener();
        cycleReportListener.start();
    }
}