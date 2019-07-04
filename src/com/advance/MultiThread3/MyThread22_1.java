package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/2 14:37
 * @Description:
 */
public class MyThread22_1 extends Thread{
    private ThreadDomain22 td;

    public MyThread22_1(ThreadDomain22 td)
    {
        this.td = td;
    }

    public void run()
    {
        td.setUserNamePassword("B", "B");
    }
}