package com.advance.MultiThread3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/22 10:08
 * @Description:
 */
public class ThreadDomain40 {
    private ReentrantLock lock = new ReentrantLock();

    public void testMethod1()
    {
        try
        {
            lock.lock();
            System.out.println("testMethod1 getHoldCount = " + lock.getHoldCount());
            testMethod2();
        }
        finally
        {
            lock.unlock();
        }
    }

    public void testMethod2()
    {
        try
        {
            lock.lock();
            System.out.println("testMethod2 getHoldCount = " + lock.getHoldCount());
        }
        finally
        {
            lock.unlock();
        }
    }



    public static void main(String[] args)
    {
        ThreadDomain40 td = new ThreadDomain40();
        td.testMethod1();
    }


}