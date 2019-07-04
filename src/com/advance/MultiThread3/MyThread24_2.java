package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/2 16:39
 * @Description:
 */
public class MyThread24_2 extends Thread{
    private ThreadDomain24 td;

    public MyThread24_2(ThreadDomain24 td)
    {
        this.td = td;
    }

    public void run()
    {
        td.printC();
    }
}