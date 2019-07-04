package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/2 16:39
 * @Description:
 */
public class MyThread24_main {
    public static void main(String[] args)
    {
        ThreadDomain24 td = new ThreadDomain24();
        MyThread24_0 mt0 = new MyThread24_0();
        MyThread24_1 mt1 = new MyThread24_1();
        MyThread24_2 mt2 = new MyThread24_2(td);
        mt0.start();
        mt1.start();
        mt2.start();
    }
}