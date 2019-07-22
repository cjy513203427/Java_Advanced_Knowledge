package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/19 10:00
 * @Description:
 */
public class MyThread36_0 extends Thread{
    private ThreadDomain36 td;

    public MyThread36_0(ThreadDomain36 td)
    {
        this.td = td;
    }

    public void run()
    {
        td.methodA();
    }
}