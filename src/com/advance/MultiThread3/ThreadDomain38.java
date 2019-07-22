package com.advance.MultiThread3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/19 10:50
 * @Description:
 */
public class ThreadDomain38 {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void await()
    {
        try
        {
            lock.lock();
            System.out.println("await时间为：" + System.currentTimeMillis());
            condition.await();
            System.out.println("await等待结束");
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

    public void signal()
    {
        try
        {
            lock.lock();
            System.out.println("signal时间为：" + System.currentTimeMillis());
            condition.signal();
            System.out.println("signal等待结束");

        }
        finally
        {
            lock.unlock();
        }
    }
}