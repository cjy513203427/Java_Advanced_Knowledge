package com.advance.MultiThread3.MyThread;

import com.advance.MultiThread3.ThreadDomain.ThreadDomain18;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/26 16:45
 * @Description:
 */
public class MyThread18 extends Thread{
    public void run()
    {
        ThreadDomain18 td = new ThreadDomain18();
        td.print1();
    }

    public static void main(String[] args)
    {
        MyThread18 mt = new MyThread18();
        mt.start();
    }

}