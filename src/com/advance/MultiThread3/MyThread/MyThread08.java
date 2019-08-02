package com.advance.MultiThread3.MyThread;

import sun.awt.windows.ThemeReader;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/21 16:40
 * @Description:
 */
public class MyThread08 {

    static class MyThread08_0 extends Thread {
        public void run() {
            long beginTime = System.currentTimeMillis();
            for (int j = 0; j < 1000000; j++) {}
            long endTime = System.currentTimeMillis();
            System.out.println("★★★★ MyThread08_0 use time = " +
                    (endTime - beginTime));
        }
    }

    static class MyThread08_1 extends Thread {
        public void run()
        {
            long beginTime = System.currentTimeMillis();
            for (int j = 0; j < 1000000; j++){}
            long endTime = System.currentTimeMillis();
            System.out.println("☆☆☆☆ MyThread08_1 use time = " +
                    (endTime - beginTime));
        }
    }

    public static void main(String[] args)
    {
        for (int i = 0; i < 5; i++)
        {
            MyThread08_0 mt0 = new MyThread08_0();
            mt0.setPriority(5);
            mt0.start();
            MyThread08_1 mt1 = new MyThread08_1();
            mt1.setPriority(4);
            mt1.start();
        }
    }

}