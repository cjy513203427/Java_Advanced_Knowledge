package com.advance.MultiThread3.ProducerConsumer;

/**
 * @Author: 谷天乐
 * @Date: 2019/8/2 14:41
 * @Description:
 */
public class Producer {
    private Object lock;

    public Producer(Object lock)
    {
        this.lock = lock;
    }

    public void setValue()
    {
        try
        {
            synchronized (lock)
            {
                if (!ValueObject.value.equals(""))
                    lock.wait();
                String value = System.currentTimeMillis() + "_" + System.nanoTime();
                System.out.println("Set的值是：" + value);
                ValueObject.value = value;
                lock.notify();
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}