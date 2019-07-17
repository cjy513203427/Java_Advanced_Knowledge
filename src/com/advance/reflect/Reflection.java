package com.advance.reflect;

import org.testng.annotations.Test;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/17 16:08
 * @Description:
 */
public class Reflection implements Cloneable, Serializable
{
    private String str;
    private double d;
    public boolean b;
    public static short s;

    public Reflection()
    {

    }

    public Reflection(String str)
    {
        this.str = str;
    }

    public Reflection(String str, double d, boolean b)
    {
        this.str = str;
        this.d = d;
        this.b = b;
    }

    private void privateMethod()
    {

    }

    public String publicMethod()
    {
        privateMethod();
        return null;
    }

    public String publicMethod(int i)
    {
        return null;
    }

    public String publicMethod(int i, double d, List<String> l)
    {
        return "Reflection.publicMethod(int i, double d), i = " + i + ", d = " + d;
    }

    public static int returnOne()
    {
        return 1;
    }

    public String toString()
    {
        return "str = " + str + ", d = " + d + ", b = " + b;
    }

    //Package对象包含有关Java包的实现和规范的版本信息。测试代码为：
    @Test
    public void testPackage() throws ClassNotFoundException {
        Class<?> c = Class.forName("com.advance.reflect.Reflection");
        Package p = c.getPackage();

        System.out.println("Package.toString()：" + p.toString()); //toString()
        System.out.println("Package.getName()：" + p.getName());     // 获取包名
        System.out.println("Package.getImplementationTitle()：" + p.getImplementationTitle()); // 获取包标题
        System.out.println("Package.getImplementationVendor()：" + p.getImplementationVendor()); // 获取提供该实现的组织、供应商或公司的名称
        System.out.println("Package.getImplementationVersion()：" + p.getImplementationVersion()); // 获取该实现的版本
        System.out.println("Package.isSealed()：" + p.isSealed()); // 获取包是否密封的
    }

    //提供有关类或接口的单个字段的信息，以及对它的动态访问权限。测试代码为：
    @Test
    public void testField() throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        Class<?> c = Class.forName("com.advance.reflect.Reflection");
        Reflection r = new Reflection();
        Field f0 = c.getField("b");
        Field f1 = c.getDeclaredField("d");
        Field[] fs0 = c.getFields();
        Field[] fs1 = c.getDeclaredFields();

        System.out.print("Class.getFields()："); // 获取类中所有public字段,顺序即public的Field定义的顺序
        for (Field f : fs0)
            System.out.print(f + "\t");

        System.out.println();
        System.out.print("Class.getDeclaredFields()："); // 获取类中任意访问权限的字段,顺序即所有Field定义的顺序
        for (Field f : fs1)
            System.out.print(f + "\t");

        System.out.println();
        System.out.println("Class.getField(String name)：" + f0); // 根据name获取类中一个访问权限为public的字段
        System.out.println("Class.getDeclaredField(String name)：" + f1); // 根据name获取类中一个任意访问权限的字段

        System.out.println();
        System.out.println("Field.getName()：" + f0.getName()); // 获取字段名
        System.out.println("Field.getType()：" + f0.getType()); // 获取类的类型
        System.out.println("Field.getBoolean()：" + f0.getBoolean(r));    // 获取某个实例对象该Field的值，什么类型的Field就是getXXX(Object obj)
        System.out.println("Field.getModifiers()：" + f0.getModifiers()); // 以整数形式返回此Field对象的Java语言修饰符，如public、static、final等
        System.out.println("Field.isAccessible()：" + f0.isAccessible()); // 返回Field的访问权限，对private的Field赋值，必须要将accessible设置为true，如下

        System.out.println();
        f1.setAccessible(false);
        System.out.println("Before setB()：" + r);
        f1.setDouble(r, 1.1);
        System.out.println("After setB()：" + r); // 向对象的指定Field设定值
    }

    //提供关于类的单个构造方法的信息以及对它的访问权限。测试代码为：
    @Test
    public void testConstructor() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> c = Class.forName("com.advance.reflect.Reflection");
        Constructor<?> constructor = c.getConstructor(String.class);
        Constructor<?>[] constructors = c.getConstructors();

        System.out.println("Class.getConstructor(Class<?>... parameterTypes)：" + constructor); // 获取指定参数列表的构造函数
        System.out.print("Class.getConstructors()："); // 获取所有的构造函数

        for (Constructor<?> con : constructors)
            System.out.print(con + "\t");
        System.out.println("\n");

        System.out.println("Constructor.getName()：" + constructor.getName()); // 获取构造函数名,没什么意义,肯定是和类同名
        System.out.println("Constructor.getModifiers()：" + constructor.getModifiers()); // 获取以整数形式返回的此Constructor对象的Java语言修饰符，如public、static、final等
        System.out.println("Constructor.isAccessible()：" + constructor.isAccessible()); // 获取该Constructor的访问权限
        System.out.println("Constructor.getParameterTypes()：" + constructor.getParameterTypes()[0]); // 获取Constructor的参数类型，是个数组
        System.out.println("Constructor.isVarArgs()：" + constructor.isVarArgs()); // 获取此Constructor中是否带了可变数量的参数，即例如"String... str"类型的参数

        System.out.println();
        Reflection r = (Reflection)constructor.newInstance("123"); // 根据指定的构造方法实例化出一个类的实例来,重要
        System.out.println("Constructor.newInstance()：" + r);
    }

    //提供关于类或接口上单独某个方法（以及如何访问该方法）的信息，所反映的方法可能是类方法或实例方法。测试代码为：
    @Test
    public void testMethod() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Reflection r = new Reflection();
        Class<?> c = Class.forName("com.advance.reflect.Reflection");
        Method md0 = c.getMethod("publicMethod", int.class, double.class, List.class);
        Method md1 = c.getDeclaredMethod("privateMethod", new Class[0]);
        Method[] ms0 = c.getMethods();
        Method[] ms1 = c.getDeclaredMethods();

        System.out.println("Method.getMethod()：" + md0); // 根据方法名和参数列表获取指定的public方法
        System.out.println("Method.getDeclaredMethod()：" + md1); // 根据方法名和参数列表获取指定的任意访问权限的方法,但不包括继承的方法

        System.out.print("Method.getMethods()："); // 获取此类包括其父类中所有的public方法
        for (Method m : ms0)
            System.out.print(m + "\t");
        System.out.println();

        System.out.print("Method.getDeclaredMethods()："); // 返回此类中所有的方法(无访问权限限制),但不包括继承的方法
        for (Method m : ms1)
            System.out.print(m + "\t");
        System.out.println("\n");

        System.out.println("Method.getName()：" + md0.getName()); // 获取方法的名字
        System.out.println("Method.isAccessible()：" + md0.isAccessible()); // 获取方法的访问属性
        System.out.println("Method.isVarArgs()：" + md0.isVarArgs()); // 获取方法是否带有可变数量的参数
        System.out.println("Method.getReturnType()：" + md0.getReturnType()); // 获取方法的返回类型
        System.out.println("Method.getParameterTypes()：" + md0.getParameterTypes()[0] + ", " + md0.getParameterTypes()[1] + ", " + md0.getParameterTypes()[2]); // 获取方法的参数类型，数组形式，注意一下和下面的方法的区别
        System.out.println("Method.getGenericParameterTypes()：" + md0.getGenericParameterTypes()[0] + ", " + md0.getGenericParameterTypes()[1] + ", " + md0.getGenericParameterTypes()[2]); // 获取方法的参数化（带泛型）类型，数组形式

        System.out.println();
        System.out.println(md0.invoke(r, 1, 2.2, new ArrayList<String>())); // 反射调用方法，重要
    }
}