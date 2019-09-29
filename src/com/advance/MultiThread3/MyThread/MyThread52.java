package com.advance.MultiThread3.MyThread;

import java.util.Date;
import java.util.concurrent.Exchanger;

/**
 * @Author: 谷天乐
 * @Date: 2019/9/29 21:57
 * @Description:
 */
public class MyThread52 extends Thread{
    private String str;
    private Exchanger<String> exchanger;
    private int sleepSecond;

    public MyThread52(String str, Exchanger<String> exchanger, int sleepSecond) {
        this.str = str;
        this.exchanger = exchanger;
        this.sleepSecond = sleepSecond;
    }

    public void run() {
        try {
            System.out.println(this.getName() + "启动, 原数据为" + str + ", 时间为" + new Date());
            Thread.sleep(sleepSecond * 1000);
            str = exchanger.exchange(str);
            System.out.println(this.getName() + "交换了数据, 交换后的数据为" + str + ", 时间为" + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<String>();
        MyThread52 et0 = new MyThread52("111", exchanger, 3);
        MyThread52 et1 = new MyThread52("222", exchanger, 2);

        et0.start();
        et1.start();
    }
}