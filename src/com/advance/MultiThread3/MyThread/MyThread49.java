package com.advance.MultiThread3.MyThread;

import java.util.concurrent.*;

/**
 * @Author: 谷天乐
 * @Date: 2019/9/16 18:45
 * @Description:
 */
public class MyThread49 {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cachedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }
    }
}