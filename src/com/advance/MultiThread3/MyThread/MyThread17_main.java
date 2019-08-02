package com.advance.MultiThread3.MyThread;

import com.advance.MultiThread3.ThreadDomain.ThreadDomain17;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/26 15:32
 * @Description:
 */
public class MyThread17_main {
    public static void main(String[] args) {
        ThreadDomain17 td = new ThreadDomain17();
        MyThread17_0 mt0 = new MyThread17_0(td);
        mt0.setName("A");
        MyThread17_1 mt1 = new MyThread17_1(td);
        mt1.setName("B");
        mt0.start();
        mt1.start();
    }
}