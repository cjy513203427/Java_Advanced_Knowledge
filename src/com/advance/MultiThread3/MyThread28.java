package com.advance.MultiThread3;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/4 14:33
 * @Description:
 */
public class MyThread28 {
    //使用CountDownLatch来等待计算线程执行完
    static CountDownLatch countDownLatch = new CountDownLatch(10);
    public int inc = 0;
    public synchronized void increase() {
        inc++;
    }

    public static synchronized void main(String[] args) throws InterruptedException {
        final MyThread28 test = new MyThread28();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                    countDownLatch.countDown();

                }
            }.start();
        }
        countDownLatch.await();
        System.out.println(test.inc);
    }

}