package com.advance.MultiThread3.ProducerConsumer;

/**
 * @Author: 谷天乐
 * @Date: 2019/8/2 14:41
 * @Description:
 */
public class Customer {
    private Object lock;

    public Customer(Object lock)
    {
        this.lock = lock;
    }

    public void getValue()
    {
        try
        {
            synchronized (lock)
            {
                if (ValueObject.value.equals(""))
                    lock.wait();
                System.out.println("Get的值是：" + ValueObject.value);
                ValueObject.value = "";
                lock.notify();
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}