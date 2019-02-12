package com.advance.abstract_and_interface;
/*
 * @Author 谷天乐
 * @Description 接口方法必须被实现
 * @Date 2019/1/16 17:27
 * @Param
 * @return
 **/
public interface Programming {

    public void writeJava();
    //abstract编译器会报redundant,说明接口里的普通方法实际上是抽象方法，所以必须被实现
    public abstract void writePython();
}
