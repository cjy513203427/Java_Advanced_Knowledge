package com.advance.MultiThread3.ThreadDomain;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/15 18:47
 * @Description:
 */
public class ThreadDomain34 {

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

}