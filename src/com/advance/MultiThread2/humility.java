package com.advance.MultiThread2;

/**
 * @Auther: 谷天乐
 * @Date: 2018/10/25 16:28
 * @Description:
 * yield()方法将线程从运行状态(Running)转化成就绪状态(Runnable)
 * 将CPU资源让给其他线程
 */
class hello10 implements Runnable {
    public void run() {
        for(int i=0;i<5;++i){
            System.out.println(Thread.currentThread().getName()+"运行"+i);
            if(i==3){
                System.out.println("线程的礼让");
                Thread.currentThread().yield();
            }
        }
    }

    public static void main(String[] args) {
        Thread h1=new Thread(new hello10(),"A");
        Thread h2=new Thread(new hello10(),"B");
        h1.start();
        h2.start();

    }
}