package com.advance.MultiThread2;

/**
 * @Auther: 谷天乐
 * @Date: 2018/10/25 16:36
 * @Description:
 */
class hello11 implements Runnable {
    public void run() {
        for(int i=0;i<10;++i){
            //所谓同步就是在统一时间段中只有有一个线程运行
            //不加synchronized的话，在1s内有多个线程运行，运行多次count--
            synchronized (this) {
                if (count > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(count--);
                }
            }
        }
    }

    public static void main(String[] args) {
        hello11 he=new hello11();
        Thread h1=new Thread(he);
        Thread h2=new Thread(he);
        Thread h3=new Thread(he);
        h1.start();
        h2.start();
        h3.start();
    }
    private int count=5;
}