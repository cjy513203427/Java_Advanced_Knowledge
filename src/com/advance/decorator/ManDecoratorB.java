package com.advance.decorator;

/**
 * @Auther: 谷天乐
 * @Date: 2018/9/26 22:47
 * @Description:
 */

public class ManDecoratorB extends Decorator {

    public void eat() {
        super.eat();
        System.out.println("===============");
        System.out.println("ManDecoratorB类");
    }
}