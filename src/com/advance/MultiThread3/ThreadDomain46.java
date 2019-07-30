package com.advance.MultiThread3;

import java.util.Date;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/30 11:44
 * @Description:
 */
public class ThreadDomain46 extends ReentrantReadWriteLock {
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

    public void read()
    {
        try
        {
            readLock().lock();
            System.out.println(Thread.currentThread().getName() + "获得了读锁, 时间为" +
                    new Date());
            Thread.sleep(10000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            readLock().unlock();
        }
    }

    public static void main(String[] args)
    {
        final ThreadDomain46 td = new ThreadDomain46();
        Runnable readRunnable = new Runnable()
        {
            public void run()
            {
                td.read();
            }
        };
        Runnable writeRunnable = new Runnable()
        {
            public void run()
            {
                td.write();
            }
        };
        Thread t0 = new Thread(readRunnable);
        Thread t1 = new Thread(writeRunnable);
        t0.start();
        t1.start();
    }
}