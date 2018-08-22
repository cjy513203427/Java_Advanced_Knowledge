package com.advance.dynamic_proxy;/**
 * Created by hasee on 2018/8/22.
 */

/**
 * @Auther: 谷天乐
 * @Date: 2018/8/22 19:35
 * @Description:
 */
public class RealSubject implements Subject
{
    @Override
    public void rent()
    {
        System.out.println("I want to rent my house");
    }

    @Override
    public void hello(String str)
    {
        System.out.println("hello: " + str);
    }
}