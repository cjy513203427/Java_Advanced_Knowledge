package com.advance.MultiThread3;

import java.util.Date;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/2 14:33
 * @Description:
 */
public class ThreadDomain22 {
    private String userNameParam;
    private String passwordParam;
    private String anyString = new String();

    public void setUserNamePassword(String userName, String password)
    {
        try
        {
            synchronized (anyString)
            {
                System.out.println("线程名称为：" + Thread.currentThread().getName() +
                        "在 " + new Date() + " 进入同步代码块");
                userNameParam = userName;
                Thread.sleep(3000);
                passwordParam = password;
                System.out.println("线程名称为：" + Thread.currentThread().getName() +
                        "在 " + new Date() + " 离开同步代码块");
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}