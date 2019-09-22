package com.advance.MultiThread3.MyThread;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: 谷天乐
 * @Date: 2019/9/22 20:43
 * @Description: WorkThread
 */
public class MyThread50_0 extends Thread
{
    private CountDownLatch cdl;
    private int sleepSecond;

    public MyThread50_0(String name, CountDownLatch cdl, int sleepSecond)
    {
        super(name);
        this.cdl = cdl;
        this.sleepSecond = sleepSecond;
    }

    public void run()
    {
        try
        {
            System.out.println(this.getName() + "启动了，时间为" + new Date());
            Thread.sleep(sleepSecond * 1000);
            cdl.countDown();
            System.out.println(this.getName() + "执行完了，时间为" + new Date());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}