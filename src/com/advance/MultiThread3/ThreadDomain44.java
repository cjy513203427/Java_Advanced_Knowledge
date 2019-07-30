package com.advance.MultiThread3;

import java.util.Date;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/25 09:19
 * @Description:
 */
public class ThreadDomain44 extends ReentrantReadWriteLock
{
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
        final ThreadDomain44 td = new ThreadDomain44();
        Runnable readRunnable = new Runnable()
        {
            public void run()
            {
                td.read();
            }
        };
        Thread t0 = new Thread(readRunnable);
        Thread t1 = new Thread(readRunnable);
        t0.start();
        t1.start();
    }
}