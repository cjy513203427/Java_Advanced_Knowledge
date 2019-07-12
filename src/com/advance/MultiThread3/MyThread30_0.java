package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/11 16:27
 * @Description:
 */
public class MyThread30_0 extends Thread {
    private Object lock;

    public MyThread30_0(Object lock)
    {
        this.lock = lock;
    }

    public void run()
    {
        try
        {
            synchronized (lock)
            {
                System.out.println("开始------wait time = " + System.currentTimeMillis());
                lock.wait();
                System.out.println("结束------wait time = " + System.currentTimeMillis());
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}