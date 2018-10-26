package com.advance.MultiThread2;

/**
 * @Auther: 谷天乐
 * @Date: 2018/10/25 15:21
 * @Description:
 * 如果一个类继承Thread，则不适合资源共享。
 * 但是如果实现了Runable接口的话，则很容易的实现资源共享。
 */
class hello3 extends Thread {
    public void run() {
        for (int i = 0; i < 7; i++) {
            if (count > 0) {
                System.out.println("count= " + count--);
            }
        }
    }

    public static void main(String[] args) {
        hello3 h1 = new hello3();
        hello3 h2 = new hello3();
        hello3 h3 = new hello3();
        h1.start();
        h2.start();
        h3.start();
    }

    private int count = 5;
}