package com.advance.MultiThread3.ThreadDomain;

/**
 * @Author: 谷天乐
 * @Date: 2019/8/15 14:59
 * @Description:
 */
public class ThreadDomain50 {


    public static void main(String[] args)
    {
        System.out.println(Thread.currentThread().getThreadGroup().getParent().getName());
        System.out.println(Thread.currentThread().getThreadGroup().getParent().getParent().getName());
    }


}