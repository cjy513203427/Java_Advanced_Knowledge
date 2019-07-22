package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/19 10:23
 * @Description:
 */
public class MyThread37_1 extends Thread {
    private ThreadDomain37 td;

    public MyThread37_1(ThreadDomain37 td)
    {
        this.td = td;
    }

    public void run()
    {
        td.methodB();
    }
}