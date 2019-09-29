package com.advance.MultiThread3.MyThread;

import org.apache.hadoop.hdfs.security.token.block.DataEncryptionKey;

import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * @Author: 谷天乐
 * @Date: 2019/9/29 21:07
 * @Description:
 */
public class MyThread51 {
    public static void main(String[] args)
    {
        final Semaphore semaphore = new Semaphore(5);

        Runnable runnable = new Runnable()
        {
            public void run()
            {
                try
                {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "获得了permit,时间为" + new Date());
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "释放了permit,时间为" + new Date());

                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    semaphore.release();
                }
            }
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++)
            threads[i] = new Thread(runnable);
        for (int i = 0; i < threads.length; i++)
            threads[i].start();
    }
}