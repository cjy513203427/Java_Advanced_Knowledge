package com.advance.MultiThread3.MyThread;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/21 16:55
 * @Description:
 */
public class MyThread09 extends Thread{
    private int i = 0;

    public void run()
    {
        try
        {
            while (true)
            {
                i++;
                System.out.println(Thread.currentThread().getName()+" i = " + i);
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        try
        {
            MyThread09 mt = new MyThread09();
            mt.setDaemon(true);
            mt.start();
            Thread.sleep(5000);
            System.out.println("现在是"+Thread.currentThread().getName()+"线程");
            Thread.sleep(1);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}