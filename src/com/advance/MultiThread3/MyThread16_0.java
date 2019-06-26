package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/26 10:24
 * @Description:
 */
public class MyThread16_0 extends Thread{
    private ThreadDomain16 td;

    public MyThread16_0(ThreadDomain16 td)
    {
        this.td = td;
    }

    public void run()
    {
        td.addNum("a");
    }
}