package com.advance.gc;

import com.advance.command.OSinfo;
import com.advance.decorator.Man;

/**
 * @Author: 谷天乐
 * @Date: 2019/5/23 14:18
 * @Description:无法检测出循环引用。如父对象有一个对子对象的引用，
 * 子对象反过来引用父对象。这样，他们的引用计数永远不可能为0
 */
public class ReferenceFindTest{
    private int id;
    public static void main(String[] args) {
        ReferenceFindTest object1 = new ReferenceFindTest();
        ReferenceFindTest object2 = new ReferenceFindTest();

        object1.id = object2.id;
        object2.id = object1.id;

        object1 = null;
        object2 = null;
    }
}