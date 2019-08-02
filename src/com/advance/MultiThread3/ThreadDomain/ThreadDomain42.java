package com.advance.MultiThread3.ThreadDomain;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/22 10:59
 * @Description:
 */
public class ThreadDomain42 extends ReentrantLock {
    public void waitMethod()
    {
        try
        {
            lock();
            Thread.sleep(Integer.MAX_VALUE);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        final ThreadDomain42 td = new ThreadDomain42();
        Runnable runnable = new Runnable()
        {
            public void run()
            {
                td.waitMethod();
            }
        };
        Thread t0 = new Thread(runnable);
        t0.start();
        Thread.sleep(500);
        Thread t1 = new Thread(runnable);
        t1.start();
        Thread.sleep(500);
        Thread t2 = new Thread(runnable);
        t2.start();
        Thread.sleep(500);
        System.out.println("t0 is waiting？" + td.hasQueuedThread(t0));
        System.out.println("t1 is waiting？" + td.hasQueuedThread(t1));
        System.out.println("t2 is waiting？" + td.hasQueuedThread(t2));
        System.out.println("Is any thread waiting？" + td.hasQueuedThreads());
    }
}