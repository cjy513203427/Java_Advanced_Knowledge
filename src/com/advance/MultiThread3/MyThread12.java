package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/24 17:34
 * @Description:
 */
public class MyThread12 extends Thread{

    static
    {
        System.out.println("静态块的打印：" +
                Thread.currentThread().getName());
    }

    public MyThread12()
    {
        System.out.println("构造方法的打印：" +
                Thread.currentThread().getName());
    }

    public void run()
    {
        System.out.println("run()方法的打印：" +
                Thread.currentThread().getName());
    }



    public static void main(String[] args)
    {
        MyThread12 mt = new MyThread12();
        mt.start();
    }


}