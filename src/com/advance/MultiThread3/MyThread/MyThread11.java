package com.advance.MultiThread3.MyThread;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/24 09:37
 * @Description:
 */
public class MyThread11 extends Thread{
    public void run()
    {
        try
        {
            int secondValue = (int)(Math.random() * 1000);
            System.out.println(secondValue);
            Thread.sleep(secondValue);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception
    {
        MyThread11 mt = new MyThread11();
        mt.start();
        mt.join();
        System.out.println("MyThread11执行完毕之后我再执行");
    }
}