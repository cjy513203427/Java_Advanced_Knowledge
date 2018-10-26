package com.advance.MultiThread2;

/**
 * @Auther: 谷天乐
 * @Date: 2018/10/25 15:32
 * @Description:取得线程的名称
 */
class hello4 implements Runnable {
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        hello4 he = new hello4();
        new Thread(he,"A").start();
        new Thread(he,"B").start();
        new Thread(he).start();
    }
}