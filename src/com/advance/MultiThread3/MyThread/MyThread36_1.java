package com.advance.MultiThread3.MyThread;

import com.advance.MultiThread3.ThreadDomain.ThreadDomain36;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/19 10:01
 * @Description:
 */
public class MyThread36_1 extends Thread {
    private ThreadDomain36 td;

    public MyThread36_1(ThreadDomain36 td)
    {
        this.td = td;
    }

    public void run()
    {
        td.methodB();
    }
}