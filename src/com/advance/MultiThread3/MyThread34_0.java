package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/15 18:50
 * @Description:
 */
public class MyThread34_0 extends Thread {

    private Object lock;

    public MyThread34_0(Object lock)
    {
        this.lock = lock;
    }

    public void run()
    {
        ThreadDomain34 td = new ThreadDomain34();
        td.testMethod(lock);
    }
}