package com.advance.MultiThread3.ThreadDomain;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/26 10:25
 * @Description:
 */
public class ThreadDomain16 {
    private int num = 0;

    public void addNum(String userName)
    {
        try
        {
            if ("a".equals(userName))
            {
                num = 100;
                System.out.println("a set over!");
                Thread.sleep(2000);
            }
            else
            {
                num = 200;
                System.out.println("b set over!");
            }
            System.out.println(userName + " num = " + num);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}