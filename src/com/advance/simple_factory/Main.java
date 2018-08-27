package com.advance.simple_factory;

/**
 * @Auther: 谷天乐
 * @Date: 2018/8/23 21:04
 * @Description:简单工厂模式
 */
public class Main {
    public static void main(String[] args) {
        INoodles noodles = SimpleNoodlesFactory.createNoodles(SimpleNoodlesFactory.TYPE_GK);
        noodles.desc();
    }
}