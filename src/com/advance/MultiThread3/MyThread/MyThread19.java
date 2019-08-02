package com.advance.MultiThread3.MyThread;

import com.advance.MultiThread3.ThreadDomain.ThreadDomain19;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/26 17:21
 * @Description:
 */
public class MyThread19 extends Thread {
    private ThreadDomain19 td;

    public MyThread19(ThreadDomain19 td)
    {
        this.td = td;
    }

    public void run()
    {
        td.testMethod();
    }

    public static void main(String[] args)
    {
        ThreadDomain19 td = new ThreadDomain19();
        MyThread19 mt0 = new MyThread19(td);
        MyThread19 mt1 = new MyThread19(td);
        mt0.start();
        mt1.start();
    }
}