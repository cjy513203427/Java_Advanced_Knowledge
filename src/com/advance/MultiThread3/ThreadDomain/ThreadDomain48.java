package com.advance.MultiThread3.ThreadDomain;

import com.advance.MultiThread3.ProducerConsumer.ValueObject;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 谷天乐
 * @Date: 2019/8/2 15:02
 * @Description:
 */
public class ThreadDomain48 extends ReentrantLock
{
    private Condition condition = newCondition();

    public void set()
    {
        try
        {
            lock();
            while (!"".equals(ValueObject.value))
                condition.await();
            ValueObject.value = "123";
            System.out.println(Thread.currentThread().getName() + "生产了value, value的当前值是" + ValueObject.value);
            condition.signal();
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

    public void get()
    {
        try
        {
            lock();
            while ("".equals(ValueObject.value))
                condition.await();
            ValueObject.value = "";
            System.out.println(Thread.currentThread().getName() + "消费了value, value的当前值是" + ValueObject.value);
            condition.signal();
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
}