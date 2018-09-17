package com.advance.singleton;

/**
 * @Auther: 谷天乐
 * @Date: 2018/9/17 21:02
 * @Description:
 */

public class Singleton {
    private volatile static Singleton singleton;
    private Singleton (){}
    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        Singleton s1 = getSingleton();
        Singleton s2 = getSingleton();
        System.out.println(s1==s2);
    }
}
