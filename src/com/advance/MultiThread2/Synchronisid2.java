package com.advance.MultiThread2;

/**
 * @Auther: 谷天乐
 * @Date: 2018/10/25 16:54
 * @Description:
 */
class hello12 implements Runnable {
    public void run() {
        for (int i = 0; i < 10; ++i) {
            sale();
        }
    }

    public synchronized void sale() {
        if (count > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(count--);
        }
    }

    public static void main(String[] args) {
        hello12 he = new hello12();
        Thread h1 = new Thread(he);
        Thread h2 = new Thread(he);
        Thread h3 = new Thread(he);
        h1.start();
        h2.start();
        h3.start();
    }

    private int count = 5;
}