package com.advance.MultiThread3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/19 10:21
 * @Description:
 */
public class ThreadDomain37 {
    private Lock lock = new ReentrantLock();

    public void methodA()
    {
        try
        {
            lock.lock();
            System.out.println("MethodA begin ThreadName = " + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("MethodA end ThreadName = " + Thread.currentThread().getName());
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

    public synchronized void methodB()
    {
        System.out.println("MethodB begin ThreadName = " + Thread.currentThread().getName());
        System.out.println("MethodB begin ThreadName = " + Thread.currentThread().getName());
    }
}