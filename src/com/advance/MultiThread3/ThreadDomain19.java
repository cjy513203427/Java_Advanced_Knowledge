package com.advance.MultiThread3;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/26 17:20
 * @Description:
 */
public class ThreadDomain19 {
    public synchronized void testMethod()
    {
        try
        {
            System.out.println("进入 ThreadDomain19.testMethod(), currentThread = " +
                    Thread.currentThread().getName());
            long l = 5;
            while (true)
            {
                long lo = 2 / l;
                Thread.sleep(1000);
                l--;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}