package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/24 17:39
 * @Description:
 */
public class MyThread13 extends Thread{
    public MyThread13()
    {
        System.out.println("MyThread13----->Begin");
        System.out.println("Thread.currentThread().getName()----->" +
                Thread.currentThread().getName());
        System.out.println("this.getName()----->" + this.getName());
        System.out.println("MyThread13----->end");
    }

    public void run()
    {
        System.out.println("run----->Begin");
        System.out.println("Thread.currentThread().getName()----->" +
                Thread.currentThread().getName());
        System.out.println("this.getName()----->" + this.getName());
        System.out.println("run----->end");
    }



    public static void main(String[] args)
    {
        MyThread13 mt = new MyThread13();
        mt.start();
    }


}