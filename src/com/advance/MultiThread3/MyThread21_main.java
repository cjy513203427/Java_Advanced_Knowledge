package com.advance.MultiThread3;

public class MyThread21_main {
    public static void main(String[] args) throws Exception
    {
        ThreadDomain21 td = new ThreadDomain21();
        MyThread21_0 mt0 = new MyThread21_0(td);
        mt0.setName("A");
        MyThread21_1 mt1 = new MyThread21_1(td);
        mt1.setName("B");
        mt0.start();
        Thread.sleep(100);
        mt1.start();
    }
}
