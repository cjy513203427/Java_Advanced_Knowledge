package com.advance.MultiThread3;

public class MyThread29_main {
    public static void main(String[] args)
    {
        ThreadDomain29 dl = new ThreadDomain29();
        MyThread29_0 t0 = new MyThread29_0(dl);
        MyThread29_1 t1 = new MyThread29_1(dl);
        t0.start();
        t1.start();

        while(true);
    }
}
