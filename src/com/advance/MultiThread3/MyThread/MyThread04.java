package com.advance.MultiThread3.MyThread;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/21 11:27
 * @Description:
 */
public class MyThread04 extends Thread{
    public void run()
    {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args)
    {
        MyThread04 mt0 = new MyThread04();
        MyThread04 mt1 = new MyThread04();
        MyThread04 mt2 = new MyThread04();

        mt0.start();
        mt1.start();
        mt2.start();
    }
}