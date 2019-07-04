package com.advance.MultiThread3;

import java.util.Date;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/2 15:02
 * @Description:
 */
public class ThreadDomain23 {
    public void testMethod1(MyObject mo)
    {
        try
        {
            synchronized (mo)
            {
                System.out.println("testMethod1__getLock time = " +
                        new Date() + ", run ThreadName = " +
                        Thread.currentThread().getName());
                Thread.sleep(0);
                System.out.println("testMethod1__releaseLock time = " +
                        new Date() + ", run ThreadName = " +
                        Thread.currentThread().getName());
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}