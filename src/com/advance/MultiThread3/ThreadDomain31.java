package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/15 17:41
 * @Description:
 */
public class ThreadDomain31 {
    public void testMethod(Object lock)
    {/*
        try
        {*/
            synchronized (lock)
            {
                System.out.println(Thread.currentThread().getName() + " Begin wait()");
                //lock.wait();
                System.out.println(Thread.currentThread().getName() + " End wait");
            }
        //}
       /* catch (InterruptedException e)
        {
            e.printStackTrace();
        }*/
    }
}