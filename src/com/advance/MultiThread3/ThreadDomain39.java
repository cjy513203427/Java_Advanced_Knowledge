package com.advance.MultiThread3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/22 09:43
 * @Description:
 */
public class ThreadDomain39 {
    private Lock lock = new ReentrantLock(false);

    public void testMethod()
    {
        try
        {
            lock.lock();
            System.out.println("ThreadName" + Thread.currentThread().getName() + "获得锁");
        }
        finally
        {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception
    {
        final ThreadDomain39 td = new ThreadDomain39();
        Runnable runnable = new Runnable()
        {
            public void run()
            {
                System.out.println("线程" + Thread.currentThread().getName() + "运行了");
                td.testMethod();
            }
        };
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++)
            threads[i] = new Thread(runnable);
        for (int i = 0; i < 5; i++)
            threads[i].start();
    }
}