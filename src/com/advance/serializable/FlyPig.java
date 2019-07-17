package com.advance.serializable;

import java.io.Serializable;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/17 10:09
 * @Description:
 */
public class FlyPig  implements Serializable {
    //不设置serialVersionUID，Java会自动帮我们设置serialVersionUID
    //手动设置serialVersionUID可以确保我们反序列化成功，否则报InvalidClassException，serialVersionUID前后不一致
    private static final long serialVersionUID = 1L;
    private static String AGE = "269";
    private String name;
    private String color;
    //transient 修饰的属性，是不会被序列化的
    transient private String car;

    private String addTip;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getAddTip() {
        return addTip;
    }

    public void setAddTip(String addTip) {
        this.addTip = addTip;
    }

    @Override
    public String toString() {
        return "FlyPig{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", car='" + car + '\'' +
                ", AGE='" + AGE + '\'' +
                //", addTip='" + addTip + '\'' +
                '}';
    }
}