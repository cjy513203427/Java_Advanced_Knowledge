package com.advance.MultiThread3.MyThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: 谷天乐
 * @Date: 2019/9/16 17:34
 * @Description:
 */
public class MyThread47{
    static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        for(int i =0;i<10;i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        }

}