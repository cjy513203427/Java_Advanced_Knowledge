package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/4 14:08
 * @Description:
 */
public class MyThread27 extends Thread{
    private ThreadDomain27 td;

    public MyThread27(ThreadDomain27 td)
    {
        this.td = td;
    }

    public void run()
    {
        td.addNum();
    }

    public static void main(String[] args)
    {
        try
        {
            ThreadDomain27 td = new ThreadDomain27();
            MyThread27[] mt = new MyThread27[5];
            for (int i = 0; i < mt.length; i++)
            {
                mt[i] = new MyThread27(td);
            }
            for (int i = 0; i < mt.length; i++)
            {
                mt[i].start();
            }
            Thread.sleep(1000);
            System.out.println(ThreadDomain27.aiRef.get());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}