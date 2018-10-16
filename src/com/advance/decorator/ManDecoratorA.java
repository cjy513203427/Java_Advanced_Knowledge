package com.advance.decorator;

/**
 * @Auther: 谷天乐
 * @Date: 2018/9/26 22:47
 * @Description:
 */

public class ManDecoratorA extends Decorator {

    public void eat() {
        super.eat();
        reEat();
        System.out.println("ManDecoratorA类");
    }

    public void reEat() {
        System.out.println("再吃一顿饭");
    }
}