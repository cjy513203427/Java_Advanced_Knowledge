package com.advance.MultiThread3.MyThread;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/2 15:03
 * @Description:
 */
public class MyThread23_1 extends Thread {
    private MyObject mo;

    public MyThread23_1(MyObject mo)
    {
        this.mo = mo;
    }

    public void run()
    {
        mo.speedPrintString();
    }
}