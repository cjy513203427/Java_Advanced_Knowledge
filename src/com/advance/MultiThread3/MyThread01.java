package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/20 10:28
 * @Description:
 */
public class MyThread01 implements Runnable
{
    public void run()
    {
        for (int i = 0; i < 50; i++)
        {
            System.out.println(Thread.currentThread().getName() + "在运行!");
        }
    }

    public static void main(String[] args)
    {
        MyThread01 mt0 = new MyThread01();
        Thread t = new Thread(mt0);
        //启动线程
        t.start();
        //main线程
        for (int i = 0; i < 50; i++)
        {
            System.out.println(Thread.currentThread().getName() + "在运行！");
        }
    }
}