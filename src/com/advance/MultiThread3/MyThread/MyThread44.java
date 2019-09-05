package com.advance.MultiThread3.MyThread;

/**
 * @Author: 谷天乐
 * @Date: 2019/8/15 15:10
 * @Description:
 */
public class MyThread44 extends Thread{

    public MyThread44(ThreadGroup tg, String name)
    {
        super(tg, name);
    }

    public void run()
    {
        System.out.println("ThreadName = " + Thread.currentThread().getName() +
                "准备开始死循环了");
        while (!this.isInterrupted()){}
        System.out.println("ThreadName = " + Thread.currentThread().getName() +
                "结束了");
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup tg = new ThreadGroup("我的线程组");
        MyThread44 mt = null;
        for (int i = 0; i < 3; i++)
        {
            mt = new MyThread44(tg, "线程" + i);
            mt.start();
        }
        Thread.sleep(5000);
        tg.interrupt();
        System.out.println("调用了interrupt()方法");
    }
}