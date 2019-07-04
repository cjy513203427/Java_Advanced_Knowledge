package com.advance.MultiThread3;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/4 10:33
 * @Description:
 */
public class MyThread26_0 extends Thread {
    public static volatile int num = 0;
    //使用CountDownLatch来等待计算线程执行完
    static CountDownLatch countDownLatch = new CountDownLatch(30);

    @Override
    public void run() {
        for(int j=0;j<1000;j++){
            num++;//自加操作
        }
        countDownLatch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread26_0[] mt = new MyThread26_0[30];
        //开启30个线程进行累加操作
        for(int i=0;i<mt.length;i++){
            mt[i] = new MyThread26_0();
        }
        for(int i=0;i<mt.length;i++){
            mt[i].start();
        }
        //等待计算线程执行完
        countDownLatch.await();
        System.out.println(num);
    }
}