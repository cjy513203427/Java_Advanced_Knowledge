package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/19 10:24
 * @Description:
 */
public class MyThread37_main {

    public static void main(String[] args)
    {
        ThreadDomain37 td = new ThreadDomain37();
        MyThread37_0 mt0 = new MyThread37_0(td);
        MyThread37_1 mt1 = new MyThread37_1(td);
        mt0.start();
        mt1.start();
    }

}