package com.advance.MultiThread3.MyThread;

import com.advance.MultiThread3.ThreadDomain.ThreadDomain38;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/19 10:55
 * @Description:
 */
public class MyThread38 extends Thread
{
    private ThreadDomain38 td;

    public MyThread38(ThreadDomain38 td)
    {
        this.td = td;
    }

    public void run()
    {
        td.await();
    }

    public static void main(String[] args) throws Exception
    {
        ThreadDomain38 td = new ThreadDomain38();
        MyThread38 mt = new MyThread38(td);
        mt.start();
        Thread.sleep(3000);
        td.signal();
    }
}