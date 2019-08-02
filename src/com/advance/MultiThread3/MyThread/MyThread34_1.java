package com.advance.MultiThread3.MyThread;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/15 18:50
 * @Description:
 */
public class MyThread34_1 extends Thread{

    private Object lock;

    public MyThread34_1(Object lock)
    {
        this.lock = lock;
    }

    public void run()
    {
        synchronized (lock)
        {
            lock.notifyAll();
        }
    }
}