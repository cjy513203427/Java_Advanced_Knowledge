package com.advance.MultiThread3;

public class MyThread29_0 extends Thread{
    private ThreadDomain29 dl;

    public MyThread29_0(ThreadDomain29 dl)
    {
        this.dl = dl;
    }

    public void run()
    {
        try
        {
            dl.obj1obj2();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
