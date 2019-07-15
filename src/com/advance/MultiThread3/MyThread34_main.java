package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/15 18:51
 * @Description:
 */
public class MyThread34_main {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        MyThread34_0 mt0 = new MyThread34_0(lock);
        MyThread34_0 mt1 = new MyThread34_0(lock);
        MyThread34_0 mt2 = new MyThread34_0(lock);
        mt0.start();
        mt1.start();
        mt2.start();
        Thread.sleep(1000);
        MyThread34_1 mt3 = new MyThread34_1(lock);
        mt3.start();
    }

}