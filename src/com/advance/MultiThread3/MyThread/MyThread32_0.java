package com.advance.MultiThread3.MyThread;

import com.advance.MultiThread3.ThreadDomain.ThreadDomain32;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/15 17:57
 * @Description:
 */
public class MyThread32_0 extends Thread{
    private Object lock;

    public MyThread32_0(Object lock)
    {
        this.lock = lock;
    }

    public void run()
    {
        ThreadDomain32 td = new ThreadDomain32();
        td.testMethod(lock);
    }
}