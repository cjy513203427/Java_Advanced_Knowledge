package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/26 16:44
 * @Description:
 */
public class ThreadDomain18 {
    public synchronized void print1()
    {
        System.out.println("ThreadDomain18.print1()");
        print2();
    }

    public synchronized void print2()
    {
        System.out.println("ThreadDomain18.print2()");
        print3();
    }

    public synchronized void print3()
    {
        System.out.println("ThreadDomain18.print3()");
    }
}