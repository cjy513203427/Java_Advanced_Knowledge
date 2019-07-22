package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/19 10:22
 * @Description:
 */
public class MyThread37_0 extends Thread {

    private ThreadDomain37 td;

    public MyThread37_0(ThreadDomain37 td)
    {
        this.td = td;
    }

    public void run()
    {
        td.methodA();
    }
}