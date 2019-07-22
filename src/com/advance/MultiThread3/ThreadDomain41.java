package com.advance.MultiThread3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/22 10:19
 * @Description:
 */
public class ThreadDomain41 {
    public ReentrantLock lock = new ReentrantLock();

    public void testMethod()
    {
        try
        {
            lock.lock();
            System.out.println("ThreadName = " + Thread.currentThread().getName() + "进入方法！");
            System.out.println("是否公平锁？" + lock.isFair());
            Thread.sleep(Integer.MAX_VALUE);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        final ThreadDomain41 td = new ThreadDomain41();
        Runnable runnable = new Runnable()
        {
            public void run()
            {
                td.testMethod();
            }
        };
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++)
            threads[i] = new Thread(runnable);
        for (int i = 0; i < 10; i++)
            threads[i].start();
        Thread.sleep(2000);
        System.out.println("有" + td.lock.getQueueLength() + "个线程正在等待！");
    }
}