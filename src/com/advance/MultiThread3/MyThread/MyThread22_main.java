package com.advance.MultiThread3.MyThread;

import com.advance.MultiThread3.ThreadDomain.ThreadDomain22;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/2 14:38
 * @Description:
 */
public class MyThread22_main{
    public static void main(String[] args)
    {
        ThreadDomain22 td = new ThreadDomain22();
        MyThread22_0 mt0 = new MyThread22_0(td);
        MyThread22_1 mt1 = new MyThread22_1(td);
        mt0.start();
        mt1.start();
    }
}