package com.advance.MultiThread2;

/**
 * @Auther: 谷天乐
 * @Date: 2018/10/25 14:42
 * @Description:
 */
class hello extends Thread {

    public hello() {

    }

    public hello(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行     " + i);
        }
    }

    public static void main(String[] args) {
        Thread h1=new hello("A");
        Thread h2=new hello("B");
        //普通的run方法，顺序执行，先执行A，再执行B
        /*h1.run();
        h2.run();*/
        //调用start方法，从新建(New)到就绪状态(Runnable)。
        //每次执行结果都不一样，因为线程获取CPU的时间是不一样的
        h1.start();
        h2.start();
    }

    private String name;
}