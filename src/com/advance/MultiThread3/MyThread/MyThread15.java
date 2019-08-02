package com.advance.MultiThread3.MyThread;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/24 18:18
 * @Description:
 */
public class MyThread15 extends Thread {
    public void run()
    {
        long beginTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 5000000; i++)
        {
            Thread.yield();
            count = count + i + 1;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("用时：" + (endTime - beginTime) + "毫秒！");
    }



    public static void main(String[] args)
    {
        MyThread15 mt = new MyThread15();
        mt.start();
    }


}