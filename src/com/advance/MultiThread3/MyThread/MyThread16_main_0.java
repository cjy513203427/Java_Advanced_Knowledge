package com.advance.MultiThread3.MyThread;

import com.advance.MultiThread3.ThreadDomain.ThreadDomain16;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/26 14:10
 * @Description:
 */
public class MyThread16_main_0 {
    public static void main(String[] args)
    {
        ThreadDomain16 td = new ThreadDomain16();
        MyThread16_0 mt0 = new MyThread16_0(td);
        MyThread16_1 mt1 = new MyThread16_1(td);
        mt0.start();
        mt1.start();
    }
}