package com.advance.MultiThread3.MyThread;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/21 11:35
 * @Description:
 */
public class MyThread05 extends Thread{

    public void run()
    {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args)
    {
        MyThread05 mt = new MyThread05();
        mt.run();

        try
        {
            for (int i = 0; i < 3; i++)
            {
                Thread.sleep((int)(Math.random() * 1000));
                System.out.println("run = " + Thread.currentThread().getName());
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}