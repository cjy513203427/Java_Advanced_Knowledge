package com.advance.MultiThread3.MyThread;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/24 18:04
 * @Description:
 */
public class MyThread14 extends Thread{
    public void run()
    {
        try
        {
            System.out.println("run threadName = " +
                    this.getName() + " begin");
            Thread.sleep(2000);
            System.out.println("run threadName = " +
                    this.getName() + " end");
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        MyThread14 mt = new MyThread14();
        mt.start();
    }
}