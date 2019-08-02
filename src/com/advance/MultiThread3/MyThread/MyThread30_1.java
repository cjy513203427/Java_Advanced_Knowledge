package com.advance.MultiThread3.MyThread;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/11 16:27
 * @Description:
 */
public class MyThread30_1 extends Thread {
    private Object lock;

    public MyThread30_1(Object lock)
    {
        this.lock = lock;
    }

    public void run()
    {
        synchronized (lock)
        {
            System.out.println("开始------notify time = " + System.currentTimeMillis());
            lock.notify();
            System.out.println("结束------notify time = " + System.currentTimeMillis());
        }
    }
}