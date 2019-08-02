package com.advance.MultiThread3.MyThread;

import com.advance.MultiThread3.ThreadDomain.ThreadDomain16;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/26 10:27
 * @Description:
 */
public class MyThread16_1 extends Thread {
    private ThreadDomain16 td;

    public MyThread16_1(ThreadDomain16 td)
    {
        this.td = td;
    }

    public void run()
    {
        td.addNum("b");
    }


}