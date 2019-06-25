package com.advance.MultiThread3;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/20 14:28
 * @Description:
 */
public class MyThread02 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("计算处理中...");
        Thread.sleep(3000);
        return 1;
    }

    public static void main(String[] args) {
        //构建任务
        MyThread02 t = new MyThread02();
        //执行Callable方式，需要FutureTask实现类的支持，用于接收运算结果
        FutureTask<Integer> task = new FutureTask<Integer>(t);
        //启动线程
        new Thread(task).start();
        //获取结果
        try {
            Integer integer = task.get(5000,TimeUnit.MILLISECONDS);
            System.out.println("线程执行结果："+integer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}