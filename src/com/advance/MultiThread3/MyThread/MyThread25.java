package com.advance.MultiThread3.MyThread;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/4 08:49
 * @Description:
 */
public class MyThread25 extends Thread{
    private volatile boolean isRunning = true;

    public boolean isRunning()
    {
        return isRunning;
    }

    public void setRunning(boolean isRunning)
    {
        this.isRunning = isRunning;
    }

    public void run()
    {
        System.out.println("进入run了");
        while (isRunning == true){}
        System.out.println("线程被停止了");
    }

    public static void main(String[] args) throws InterruptedException {

        MyThread25 mt = new MyThread25();
        mt.start();
        Thread.sleep(1000);
        mt.setRunning(false);
        System.out.println("已设置为false");

    }
}