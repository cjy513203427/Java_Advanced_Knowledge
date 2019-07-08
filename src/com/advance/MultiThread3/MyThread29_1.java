package com.advance.MultiThread3;

public class MyThread29_1 extends Thread{
    private ThreadDomain29 dl;

    public MyThread29_1(ThreadDomain29 dl)
    {
        this.dl = dl;
    }

    public void run()
    {
        try
        {
            dl.obj2obj1();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
