package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/26 14:10
 * @Description:
 */
public class MyThread16_main_1 {
    public static void main(String[] args)
    {
        ThreadDomain16 td0 = new ThreadDomain16();
        ThreadDomain16 td1 = new ThreadDomain16();
        MyThread16_0 mt0 = new MyThread16_0(td0);
        MyThread16_1 mt1 = new MyThread16_1(td1);
        mt0.start();
        mt1.start();
    }
}