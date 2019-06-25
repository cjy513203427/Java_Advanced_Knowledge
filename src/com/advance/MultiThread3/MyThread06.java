package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/21 15:24
 * @Description:
 */
public class MyThread06 extends Thread{
    public void run()
    {
        System.out.println("run = " + this.isAlive());
    }


    public static void main(String[] args) throws Exception
    {
        MyThread06 mt = new MyThread06();
        System.out.println("begin == " + mt.isAlive());
        mt.start();
        Thread.sleep(100);
        System.out.println("end == " + mt.isAlive());
    }
}