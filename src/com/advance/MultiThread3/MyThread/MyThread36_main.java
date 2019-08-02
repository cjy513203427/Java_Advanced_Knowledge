package com.advance.MultiThread3.MyThread;

import com.advance.MultiThread3.ThreadDomain.ThreadDomain36;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/19 10:02
 * @Description:
 */
public class MyThread36_main {

    public static void main(String[] args)
    {
        ThreadDomain36 td = new ThreadDomain36();
        MyThread36_0 mt0 = new MyThread36_0(td);
        MyThread36_1 mt1 = new MyThread36_1(td);
        mt0.start();
        mt1.start();
    }

}