package com.advance.decorator2;

/**
 * @Auther: 谷天乐
 * @Date: 2018/10/16 11:01
 * @Description:
 */
public class Rectangle implements Shape  {
    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}