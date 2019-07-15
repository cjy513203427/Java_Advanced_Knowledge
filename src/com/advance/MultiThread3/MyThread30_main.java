package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/11 16:27
 * @Description:
 */
public class MyThread30_main {
    public static void main(String[] args) throws Exception
    {
        Object lock = new Object();
        MyThread30_0 mt0 = new MyThread30_0(lock);
        mt0.start();
        Thread.sleep(3000);
        MyThread30_1 mt1 = new MyThread30_1(lock);
        mt1.start();
    }
}