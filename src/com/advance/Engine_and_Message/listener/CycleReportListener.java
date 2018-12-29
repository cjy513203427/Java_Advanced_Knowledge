package com.advance.Engine_and_Message.listener;

import com.advance.Engine_and_Message.domain.Message;
import com.advance.Engine_and_Message.domain.TaskInfo;
import com.advance.Engine_and_Message.factory.MessageQueueFactory;
import com.advance.Engine_and_Message.util.NonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: 谷天乐
 * @Date: 2018/11/7 17:45
 * @Description:
 */
public class CycleReportListener {

    // 监听间隔时长，单位为秒
    private int listenerTimes = 1;

    public int getListenerTimes() {
        return listenerTimes;
    }

    /**
     *
     * @author dong
     * @date 2017年2月22日下午2:51:27
     * @parameter
     * @return void
     * @desc 初始化
     */
    public void initialize() {
        start();
    }

    public void start() {
        new Thread(() -> {
            while (true) {
                try {
                    TaskInfo taskInfo = new TaskInfo();
                    int[] waitinglist = taskInfo.getBackrpArr();
                    if (NonUtil.isNotNon(waitinglist)) {
                        for (int crp : waitinglist) {
                            System.out.println("监控到未处理定时报表+["
                                    + waitinglist.toString() + "]");
                            Message<Object> message = new Message<>();
                            message.setMessageType("定时报表");
                            message.setObject(crp);
                            if (new MessageQueueFactory().addMessage(message)) {
                                // 加入消息队列成功，更改报表状态

                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("定时报表监听扫描异常"+ e);
                }
                try {
                    Thread.sleep(1000 * listenerTimes);
                } catch (InterruptedException e) {
                }
            }
        }).start();
    }
}
