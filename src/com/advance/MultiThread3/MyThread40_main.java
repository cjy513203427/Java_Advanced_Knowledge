package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/30 15:40
 * @Description:
 */
public class MyThread40_main {
    public static void main(String[] args) {
        ThreadDomain47 task = new ThreadDomain47();

        Thread t1=new Thread(new MyThread40_0(task));
        Thread t3=new Thread(new MyThread40_0(task));
        Thread t7=new Thread(new MyThread40_0(task));
        Thread t8=new Thread(new MyThread40_0(task));
        Thread t2 = new Thread(new MyThread40_1(task));
        Thread t4 = new Thread(new MyThread40_1(task));
        Thread t5 = new Thread(new MyThread40_1(task));
        Thread t6 = new Thread(new MyThread40_1(task));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
    }
}