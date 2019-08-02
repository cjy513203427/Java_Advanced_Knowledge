package com.advance.MultiThread3.ThreadDomain;

public class ThreadDomain21 {
    public  void otherMethod()
    {
        System.out.println("----------run--otherMethod");
    }

    public void doLongTask()
    {
        synchronized (this)
        {
            for (int i = 0; i < 100; i++)
            {
                System.out.println("synchronized threadName = " +
                        Thread.currentThread().getName() + ", i = " + (i + 1));
                try
                {
                    Thread.sleep(5);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
