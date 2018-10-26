package com.advance.MultiThread2;

/**
 * @Auther: 谷天乐
 * @Date: 2018/10/25 15:05
 * @Description:
 */
class hello2 implements Runnable {

    public hello2() {

    }

    public hello2(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行     " + i);
        }
    }

    public static void main(String[] args) {
        hello2 h1=new hello2("线程A");
        Thread demo= new Thread(h1);
        hello2 h2=new hello2("线程B");
        Thread demo1=new Thread(h2);
        demo.run();
        demo1.run();
    }

    private String name;
}