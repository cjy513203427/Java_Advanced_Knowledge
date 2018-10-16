package com.advance.decorator;

/**
 * @Auther: 谷天乐
 * @Date: 2018/9/26 22:48
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        Man man = new Man();
        ManDecoratorA md1 = new ManDecoratorA();
        ManDecoratorB md2 = new ManDecoratorB();

        md1.setPerson(man);
        md2.setPerson(md1);
        md2.eat();
    }
}