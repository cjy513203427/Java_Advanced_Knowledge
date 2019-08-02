package com.advance.MultiThread3.ThreadDomain;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/22 11:26
 * @Description:
 */
public class ThreadDomain43 extends ReentrantLock {
    public void testMethod()
    {
        try
        {
            lock();
            System.out.println(Thread.currentThread().getName() + "线程持有了锁！");
            System.out.println(Thread.currentThread().getName() + "线程是否持有锁？" +
                    isHeldByCurrentThread());
            System.out.println("是否任意线程持有了锁？" + isLocked());
        } finally
        {
            unlock();
        }
    }

    public void testHoldLock()
    {
        System.out.println(Thread.currentThread().getName() + "线程是否持有锁？" +
                isHeldByCurrentThread());
        System.out.println("是否任意线程持有了锁？" + isLocked());
    }

    public static void main(String[] args)
    {
        final ThreadDomain43 td = new ThreadDomain43();
        Runnable runnable0 = new Runnable()
        {
            public void run()
            {
                td.testMethod();
            }
        };
        Runnable runnable1 = new Runnable()
        {
            public void run()
            {
                td.testHoldLock();
            }
        };
        Thread t0 = new Thread(runnable0);
        Thread t1 = new Thread(runnable1);
        t0.start();
        t1.start();
    }
}