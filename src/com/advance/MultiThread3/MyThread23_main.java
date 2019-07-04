package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/2 15:03
 * @Description:
 */
public class MyThread23_main {
    public static void main(String[] args) throws InterruptedException {
        ThreadDomain23 td = new ThreadDomain23();
        MyObject mo = new MyObject();
        MyThread23_0 mt0 = new MyThread23_0(td, mo);
        MyThread23_1 mt1 = new MyThread23_1(mo);
        mt0.start();
        mt1.start();
    }
}