package com.advance.MultiThread3.MyThread;

import com.advance.MultiThread3.ThreadDomain.ThreadDomain23;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/2 15:02
 * @Description:
 */
public class MyThread23_0 extends Thread{
    private ThreadDomain23 td;
    private MyObject mo;

    public MyThread23_0(ThreadDomain23 td, MyObject mo)
    {
        this.td = td;
        this.mo = mo;
    }

    public void run()
    {
        td.testMethod1(mo);
    }
}