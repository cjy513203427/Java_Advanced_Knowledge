package com.advance.MultiThread3.ThreadDomain;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/26 15:29
 * @Description:
 */
public class ThreadDomain17 {
    public synchronized void methodA()
    {
        try
        {
            System.out.println("开始 methodA, 线程名称 = " +
                    Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("结束 methodA, 线程名称 = " +
                    Thread.currentThread().getName());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void methodB()
    {
        try
        {
            System.out.println("开始 methodB, 线程名称 = " +
                    Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("结束 methodB, 线程名称 = " +
                    Thread.currentThread().getName());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}