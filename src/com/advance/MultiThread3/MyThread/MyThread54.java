package com.advance.MultiThread3.MyThread;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: 谷天乐
 * @Date: 2019/10/6 23:04
 * @Description:
 */
public class MyThread54 implements Callable<String> {
    public String call() throws Exception
    {
        System.out.println("进入CallableThread的call()方法, 开始睡觉, 睡觉时间为" + new Date());
        Thread.sleep(10000);
        return "是ss12";
    }

    public static void main(String[] args) throws Exception
    {
        ExecutorService es = Executors.newCachedThreadPool();
        MyThread54 ct = new MyThread54();
        Future<String> f = es.submit(ct);
        es.shutdown();

        Thread.sleep(5000);
        System.out.println("主线程等待5秒, 当前时间为" + new Date());

        String str = f.get();
        System.out.println("Future已拿到数据, str = " + str + ", 当前时间为" + new Date());
    }
}