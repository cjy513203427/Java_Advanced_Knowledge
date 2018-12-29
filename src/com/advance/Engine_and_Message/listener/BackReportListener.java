package com.advance.Engine_and_Message.listener;

import com.advance.Engine_and_Message.domain.Message;
import com.advance.Engine_and_Message.domain.TaskInfo;
import com.advance.Engine_and_Message.factory.MessageQueueFactory;
import com.advance.Engine_and_Message.util.NonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: 谷天乐
 * @Date: 2018/11/7 17:44
 * @Description:
 */
public class BackReportListener {

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
                    // 后台报表扫描前进行预处理，检查上一次或者异常导致的执行中报表迟迟未执行出结果，将此类报表重置为待执行报表重新执行

                    TaskInfo taskInfo = new TaskInfo();

                    int[] waitinglist = taskInfo.getBackrpArr();
                    if (NonUtil.isNotNon(waitinglist)) {
                        for (int crp : waitinglist) {
                            System.out.println("监控到未处理后台报表+["
                                    + waitinglist.toString() + "]");
                            Message<Object> message = new Message<>();
                            message.setMessageType("后台报表");
                            message.setObject(crp);
                            if (new MessageQueueFactory().addMessage(message)) {
                                // 加入消息队列成功，更改报表状态
                            }

                        }
                    }
                } catch (Exception e) {
                    System.out.println("后台报表监听扫描异常" + e);
                }
                try {
                    Thread.sleep(1000 * listenerTimes);
                } catch (InterruptedException e) {

                }
            }
        }).start();
    }

}
