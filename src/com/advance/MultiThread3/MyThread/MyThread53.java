package com.advance.MultiThread3.MyThread;

import java.util.Date;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: 谷天乐
 * @Date: 2019/9/30 22:21
 * @Description: CyclicBarrierThread
 */
public class MyThread53 extends Thread{
    private CyclicBarrier cb;
    private int sleepSecond;

    public MyThread53(CyclicBarrier cb, int sleepSecond)
    {
        this.cb = cb;
        this.sleepSecond = sleepSecond;
    }

    public void run()
    {
        try
        {
            System.out.println(this.getName() + "运行了");
            System.out.println(this.getName() + "准备等待了, 时间为" + new Date());
            Thread.sleep(sleepSecond * 1000);

            cb.await();
            System.out.println(this.getName() + "结束等待了, 时间为" + new Date());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public static void main(String[] args)
    {
        Runnable runnable = new Runnable()
        {
            public void run()
            {
                System.out.println("CyclicBarrier的所有线程await()结束了，我运行了, 时间为" + new Date());
            }
        };
        //需要等待三个线程await()后再执行runnable
        CyclicBarrier cb = new CyclicBarrier(3, runnable);
        MyThread53 cbt0 = new MyThread53(cb, 3);
        MyThread53 cbt1 = new MyThread53(cb, 6);
        MyThread53 cbt2 = new MyThread53(cb, 9);
        cbt0.start();
        cbt1.start();
        cbt2.start();
    }
}