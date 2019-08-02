package com.advance.MultiThread3.MyThread;

import com.advance.MultiThread3.ThreadDomain.ThreadDomain17;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/26 15:28
 * @Description:
 */
public class MyThread17_0 extends Thread{
    private ThreadDomain17 td;

    public MyThread17_0(ThreadDomain17 td)
    {
        this.td = td;
    }

    public void run()
    {
        td.methodA();
    }
}