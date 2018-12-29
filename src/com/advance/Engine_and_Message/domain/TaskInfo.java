package com.advance.Engine_and_Message.domain;

import com.advance.Engine_and_Message.queue.EngineQueue;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: 谷天乐
 * @Date: 2018/11/8 11:31
 * @Description:
 */
public class TaskInfo {

    private int [] backrpArr;

    private ConcurrentHashMap<String, EngineQueue> queueMap = new ConcurrentHashMap<>();

    public int[] getBackrpArr() {
        return backrpArr;
    }

    public void setBackrpArr(int[] backrpArr) {
        this.backrpArr = backrpArr;
    }

    public ConcurrentHashMap<String, EngineQueue> getQueueMap() {
        return queueMap;
    }

    public void setQueueMap(ConcurrentHashMap<String, EngineQueue> queueMap) {
        this.queueMap = queueMap;
    }
}