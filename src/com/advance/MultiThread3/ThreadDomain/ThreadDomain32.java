package com.advance.MultiThread3.ThreadDomain;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/15 17:56
 * @Description:
 */
public class ThreadDomain32 {
    public void testMethod(Object lock)
    {
        try
        {
            synchronized (lock)
            {
                System.out.println("Begin wait(), ThreadName = " + Thread.currentThread().getName());
                lock.wait();
                System.out.println("End wait(), ThreadName = " + Thread.currentThread().getName());
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void synNotifyMethod(Object lock)
    {
        try
        {
            synchronized (lock)
            {
                System.out.println("Begin notify(), ThreadName = " + Thread.currentThread().getName());
                lock.notify();
                Thread.sleep(5000);
                System.out.println("End notify(), ThreadName = " + Thread.currentThread().getName());
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}