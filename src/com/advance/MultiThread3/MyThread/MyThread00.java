package com.advance.MultiThread3.MyThread;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/20 10:08
 * @Description:
 */
public class MyThread00 extends Thread{
    public void run()
    {
        for (int i = 0; i < 50; i++)
        {
            System.out.println(Thread.currentThread().getName() + "在运行!");
        }
    }


    public static void main(String[] args)
    {
        MyThread00 mt0 = new MyThread00();
        //启动线程
        mt0.start();


        //main线程
        for (int i = 0; i < 50; i++)
        {
            System.out.println(Thread.currentThread().getName() + "在运行！");
        }
    }
}