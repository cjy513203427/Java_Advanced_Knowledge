package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/26 15:31
 * @Description:
 */
public class MyThread17_1 extends Thread{
    private ThreadDomain17 td;

    public MyThread17_1(ThreadDomain17 td)
    {
        this.td = td;
    }

    public void run()
    {
        td.methodB();
    }
}