package com.advance.MultiThread3;

public class ThreadDomain20 extends Thread{
    public void doLongTimeTask() throws Exception
    {
        for (int i = 0; i < 50; i++)
        {
            System.out.println("nosynchronized threadName = " +
                    Thread.currentThread().getName() + ", i = " + (i + 1));
        }
        System.out.println();
        synchronized (this){
            for (int i = 0; i < 50; i++)
            {
                System.out.println("synchronized threadName = " +
                        Thread.currentThread().getName() + ", i = " + (i + 1));
            }
        }
    }

}
