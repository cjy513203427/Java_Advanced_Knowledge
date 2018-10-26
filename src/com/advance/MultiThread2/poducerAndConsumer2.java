package com.advance.MultiThread2;

/**
 * @Auther: 谷天乐
 * @Date: 2018/10/26 08:57
 * @Description:
 */
class Info2 {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public synchronized void set(String name, int age){
        //通过标志位flag来完成等待和唤醒的操作
        if(!flag){
            try{
                super.wait();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.name=name;
        try{
            Thread.sleep(100);
        }catch (Exception e) {
            e.printStackTrace();
        }
        this.age=age;
        flag=false;
        super.notify();
    }

    public synchronized void get(){
        if(flag){
            try{
                super.wait();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        try{
            Thread.sleep(100);
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(this.getName()+"<===>"+this.getAge());
        flag=true;
        super.notify();
    }
    private String name = "Rollen";
    private int age = 20;
    private boolean flag=false;
}

/**
 * 生产者
 * */
class Producer2 implements Runnable {
    private Info2 Info2 = null;

    Producer2(Info2 Info2) {
        this.Info2 = Info2;
    }

    public void run() {
        boolean flag = false;
        for (int i = 0; i < 25; ++i) {
            if (flag) {

                this.Info2.set("Rollen", 20);
                flag = false;
            } else {
                this.Info2.set("ChunGe", 100);
                flag = true;
            }
        }
    }
}

/**
 * 消费者类
 * */
class consumer2 implements Runnable {
    private Info2 Info2 = null;

    public consumer2(Info2 Info2) {
        this.Info2 = Info2;
    }

    public void run() {
        for (int i = 0; i < 25; ++i) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.Info2.get();
        }
    }
}

/**
 * 测试类
 * */
class hello14 {
    public static void main(String[] args) {
        Info2 Info2 = new Info2();
        Producer2 pro = new Producer2(Info2);
        consumer2 con = new consumer2(Info2);
        new Thread(pro).start();
        new Thread(con).start();
    }
}