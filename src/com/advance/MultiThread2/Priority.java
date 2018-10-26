package com.advance.MultiThread2;

/**
 * @Auther: 谷天乐
 * @Date: 2018/10/25 16:20
 * @Description:线程的优先级
 * 优先级数字越大表示优先级越高
 */
class hello9 implements Runnable {
    public void run() {
        for(int i=0;i<5;++i){
            System.out.println(Thread.currentThread().getName()+"运行"+i);
        }
    }

    public static void main(String[] args) {
        Thread h1=new Thread(new hello9(),"A");
        Thread h2=new Thread(new hello9(),"B");
        Thread h3=new Thread(new hello9(),"C");
        h1.setPriority(8);
        h2.setPriority(2);
        h3.setPriority(6);
        h1.start();
        h2.start();
        h3.start();
    }
}