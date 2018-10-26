package com.advance.MultiThread2;

/**
 * @Auther: 谷天乐
 * @Date: 2018/10/25 16:05
 * @Description:线程的休眠
 */
class hello7 implements Runnable {
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + i);
        }
    }

    public static void main(String[] args) {
        hello7 he = new hello7();
        Thread demo = new Thread(he, "线程");
        demo.start();
    }
}