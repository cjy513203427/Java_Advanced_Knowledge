package com.advance.MultiThread3.ThreadDomain;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/19 09:30
 * @Description:
 */
public class ThreadDomain35 {

    private Lock lock = new ReentrantLock();

    public void testMethod()
    {
        try
        {
            lock.lock();
            for (int i = 0; i < 2; i++)
            {
                System.out.println("ThreadName = " + Thread.currentThread().getName() + ", i  = " + i);
            }
        }
        finally
        {
            lock.unlock();
        }
    }
}