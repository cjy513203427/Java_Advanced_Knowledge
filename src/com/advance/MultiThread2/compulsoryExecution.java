package com.advance.MultiThread2;

/**
 * @Auther: 谷天乐
 * @Date: 2018/10/25 15:46
 * @Description:线程的强制执行
 */
class hello6 implements Runnable {
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        hello6 he = new hello6();
        Thread demo = new Thread(he,"线程");
        demo.start();
        for(int i=0;i<50;++i){
            //强制执行的线程必须在i=11之前执行
            //join()使main线程进入阻塞状态，main线程放弃cpu控制权，等到join()中断才轮到main继续执行
            if(i>10){
                try{
                    demo.join();  //强制执行demo
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("main 线程执行-->"+i);
        }
    }
}