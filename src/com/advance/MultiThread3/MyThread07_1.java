package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/21 16:33
 * @Description: 线程具有继承性
 */
public class MyThread07_1 extends Thread {

    public void run()
    {
        System.out.println("MyThread07_1 run priority = " +
                this.getPriority());
        MyThread07_0 thread = new MyThread07_0();
        thread.start();
    }

    public static void main(String[] args)
    {
        System.out.println("main thread begin, priority = " +
                Thread.currentThread().getPriority());
        System.out.println("main thread end, priority = " +
                Thread.currentThread().getPriority());
        MyThread07_1 thread = new MyThread07_1();
        thread.start();
    }
}