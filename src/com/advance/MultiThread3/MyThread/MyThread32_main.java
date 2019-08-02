package com.advance.MultiThread3.MyThread;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/15 17:58
 * @Description:
 */
public class MyThread32_main {
    public static void main(String[] args) throws Exception
    {
        Object lock = new Object();
        MyThread32_0 mt0 = new MyThread32_0(lock);
        mt0.start();
        MyThread32_1 mt1 = new MyThread32_1(lock);
        mt1.start();
        MyThread32_1 mt2 = new MyThread32_1(lock);
        mt2.start();
    }
}