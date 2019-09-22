package com.advance.MultiThread3.MyThread;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: 谷天乐
 * @Date: 2019/9/22 20:48
 * @Description: DoneThread
 */
public class MyThread50_1 extends Thread {
    private CountDownLatch cdl;

    public MyThread50_1(String name, CountDownLatch cdl)
    {
        super(name);
        this.cdl = cdl;
    }

    public void run()
    {
        try
        {
            System.out.println(this.getName() + "要等待了, 时间为" + new Date());
            cdl.await();
            System.out.println(this.getName() + "等待完了, 时间为" + new Date());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(3);
        MyThread50_1 dt0 = new MyThread50_1("DoneThread1", cdl);
        MyThread50_1 dt1 = new MyThread50_1("DoneThread2", cdl);
        dt0.start();
        dt1.start();
        MyThread50_0 wt0 = new MyThread50_0("WorkThread1", cdl, 2);
        MyThread50_0 wt1 = new MyThread50_0("WorkThread2", cdl, 3);
        MyThread50_0 wt2 = new MyThread50_0("WorkThread3", cdl, 4);
        wt0.start();
        wt1.start();
        wt2.start();
    }
}