package com.advance.MultiThread3;

import java.util.Date;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/25 10:11
 * @Description:
 */
public class ThreadDomain45 extends ReentrantReadWriteLock {
    public void write()
    {
        try
        {
            writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "获得了写锁, 时间为" +
                    new Date());
            Thread.sleep(10000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            writeLock().unlock();
        }
    }

    public static void main(String[] args)
    {
        final ThreadDomain45 td = new ThreadDomain45();
        Runnable readRunnable = new Runnable()
        {
            public void run()
            {
                td.write();
            }
        };
        Thread t0 = new Thread(readRunnable);
        Thread t1 = new Thread(readRunnable);
        t0.start();
        t1.start();
    }
}