package com.advance.decorator;

/**
 * @Auther: 谷天乐
 * @Date: 2018/9/26 22:47
 * @Description:
 */
public abstract class Decorator implements Person {

    protected Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    public void eat() {
        person.eat();
    }
}