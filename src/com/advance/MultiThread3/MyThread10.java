package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/21 17:49
 * @Description:
 */
public class MyThread10 extends Thread{
    public void run()
    {
        for (int i = 0; i < 500000; i++)
        {
            System.out.println("i = " + (i + 1));
        }
    }

    public static void main(String[] args)
    {
        try
        {
            MyThread10 mt = new MyThread10();
            mt.start();
            Thread.sleep(2000);
            mt.interrupt();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}