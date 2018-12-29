package com.advance.Engine_and_Message.factory;

import com.advance.Engine_and_Message.domain.TaskInfo;
import com.advance.Engine_and_Message.engine.Engine;
import com.advance.Engine_and_Message.domain.EngineDomain;
import com.advance.Engine_and_Message.queue.EngineQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: 谷天乐
 * @Date: 2018/11/7 17:42
 * @Description:
 */
public class EngineQueueFactory {

    // 引擎Map，key为引擎类型，每种类型的引擎一个队列
    private ConcurrentHashMap<String, EngineQueue> queueMap = new ConcurrentHashMap<>();

    private static EngineQueueFactory instance = null;

    /**
     * @Description: 单例模式初始化队列工厂
     * @return
     */
    public static EngineQueueFactory getInstance() {
        if (instance == null)
            instance = new EngineQueueFactory();
        return instance;
    }
    /**
     * 在项目启动的过程中进入initialize()方法
     * 根据spring-busniss.xml中initSize的大小循环
     * 先循环30次，再循环50次
     */
    public void initialize(List<EngineDomain> engineList) {
        for (EngineDomain domain : engineList) {
            EngineQueue queue = new EngineQueue(domain.getInitsize());
            for (int i = 0; i < domain.getInitsize(); i++) {
                try {
                    //运用反射获取不同类型的引擎
                    Engine engine = (Engine) Class.forName(
                            domain.getClassName()).newInstance();
                    queue.addEngine(engine);
                } catch (Exception e) {
                    System.out.println(
                            "[" + domain.getEngineType() + ","
                                    + domain.getClassName() + ","
                                    + domain.getDesc() + "] 引擎初始化失败!"+e);
                }
            }
            queueMap.put(domain.getEngineType(), queue);
            System.out.println("成功初始化[" + domain.getDesc() + "]类引擎["
                    + domain.getInitsize() + "]个");

        }
    }

    /**
     * @Description: 引擎使用完成后回收到队列中
     * @param engine
     */
    public boolean recycle(Engine engine) {
        //手动初始化引擎队列
        EngineQueue queueInitial = new EngineQueue(30);
        queueMap.put("后台报表", queueInitial);
        queueMap.put("定时报表", queueInitial);
        EngineQueue queue = queueMap.get(engine.getMessage().getMessageType());
        if (queue == null) {
            System.out.println("[" + engine.getMessage().getMessageId() + "] 消息类型["
                    + engine.getMessage().getMessageType()
                    + "]对应的处理引擎回收失败，系统中没有注册此类引擎！");
            return false;
        }
        queue.recycle(engine);
        return true;
    }

    /**
     * @Description: 请求处理消息的引擎
     * @param engineType
     * @return
     */
    public Engine getEngine(String engineType) {
        EngineQueue queue = queueMap.get(engineType);
        if (queue == null) {
            System.out.println(getClass().getName()+"[" + engineType + "]获取引擎失败，对应的处理引擎请求失败，系统中没有注册此类引擎！");
            return null;
        }
        return queue.getEngine();
    }

    /**
     * @Description: 获取引擎队列中各种类型引擎的空闲数量
     * @return
     */
    public Map<String, Integer> getEngineSize() {
        Map<String, Integer> engines = new HashMap<String, Integer>();
        for (Map.Entry<String, EngineQueue> entry : queueMap.entrySet()) {
            engines.put(entry.getKey(), entry.getValue().getEngineSize());
        }
        return engines;
    }
}
