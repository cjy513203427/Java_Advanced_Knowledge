package com.advance.MultiThread3;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/4 11:04
 * @Description:
 */
public class MyThread26_1 extends Thread {
    //使用原子操作类
    public static AtomicInteger num = new AtomicInteger(0);
    //使用CountDownLatch来等待计算线程执行完
    static CountDownLatch countDownLatch = new CountDownLatch(30);

    @Override
    public void run() {
        for(int j=0;j<1000;j++){
            num.incrementAndGet();//原子性的num++,通过循环CAS方式
        }
        countDownLatch.countDown();
    }

    public static void main(String []args) throws InterruptedException {
        MyThread26_1[] mt = new MyThread26_1[30];
        //开启30个线程进行累加操作
        for(int i=0;i<mt.length;i++){
            mt[i] = new MyThread26_1();
        }
        for(int i=0;i<mt.length;i++){
            mt[i].start();
        }
        //等待计算线程执行完
        countDownLatch.await();
        System.out.println(num);
    }
}