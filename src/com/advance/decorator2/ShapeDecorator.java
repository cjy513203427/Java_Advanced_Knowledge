package com.advance.decorator2;

/**
 * @Auther: 谷天乐
 * @Date: 2018/10/16 11:02
 * @Description:
 */

public abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    public void draw(){
        decoratedShape.draw();
    }
}
