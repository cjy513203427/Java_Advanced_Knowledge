package com.advance.MultiThread3.MyThread;

import com.advance.MultiThread3.ThreadDomain.ThreadDomain20;

public class MyThread20 extends Thread{
    private ThreadDomain20 td;

    public MyThread20(ThreadDomain20 td)
    {
        this.td = td;
    }

    public void run()
    {
        try
        {
            td.doLongTimeTask();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        ThreadDomain20 td = new ThreadDomain20();
        MyThread20 mt0 = new MyThread20(td);
        mt0.setName("A");
        MyThread20 mt1 = new MyThread20(td);
        mt1.setName("B");
        mt0.start();
        mt1.start();
    }

}
