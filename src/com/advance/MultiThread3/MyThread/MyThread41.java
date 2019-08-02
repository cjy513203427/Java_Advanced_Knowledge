package com.advance.MultiThread3.MyThread;

import com.advance.MultiThread3.ThreadDomain.ThreadDomain48;

/**
 * @Author: 谷天乐
 * @Date: 2019/8/2 15:03
 * @Description:
 */
public class MyThread41 {
    public static void main(String[] args)
    {
        final ThreadDomain48 td = new ThreadDomain48();
        Runnable producerRunnable = new Runnable()
        {
            public void run()
            {
                for (int i = 0; i < Integer.MAX_VALUE; i++)
                    td.set();
            }
        };
        Runnable customerRunnable = new Runnable()
        {
            public void run()
            {
                for (int i = 0; i < Integer.MAX_VALUE; i++)
                    td.get();
            }
        };
        Thread ProducerThread1 = new Thread(producerRunnable);
        ProducerThread1.setName("Producer1");
        Thread ProducerThread2 = new Thread(producerRunnable);
        ProducerThread2.setName("Producer2");
        Thread ConsumerThread = new Thread(customerRunnable);
        ConsumerThread.setName("Consumer");
        ProducerThread1.start();
        ProducerThread2.start();
        ConsumerThread.start();
    }
}