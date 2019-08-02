package com.advance.MultiThread3.MyThread;

import com.advance.MultiThread3.ThreadDomain.ThreadDomain47;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/30 15:39
 * @Description:SubThread
 */
public class MyThread40_1 implements Runnable {
    private ThreadDomain47 task;

    public MyThread40_1(ThreadDomain47 task) {
        this.task = task;
    }

    @Override
    public void run() {
        task.sub();
    }

}