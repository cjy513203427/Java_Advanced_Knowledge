package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/19 09:32
 * @Description:
 */
public class MyThread35 extends Thread {

    private ThreadDomain35 td;

    public MyThread35(ThreadDomain35 td)
    {
        this.td = td;
    }

    public void run()
    {
        td.testMethod();
    }

    public static void main(String[] args)
    {
        ThreadDomain35 td = new ThreadDomain35();
        MyThread35 mt0 = new MyThread35(td);
        MyThread35 mt1 = new MyThread35(td);
        MyThread35 mt2 = new MyThread35(td);
        mt0.start();
        mt1.start();
        mt2.start();
    }
}