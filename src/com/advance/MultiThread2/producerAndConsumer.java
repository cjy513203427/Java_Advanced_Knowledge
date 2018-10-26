package com.advance.MultiThread2;

/**
 * @Auther: 谷天乐
 * @Date: 2018/10/25 16:56
 * @Description:Rollen-20和chunGe-100不对应
 */
class Info {

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

    private String name = "Rollen";
    private int age = 20;
}

/**
 * 生产者
 * */
class Producer implements Runnable{
    private Info info=null;
    Producer(Info info){
        this.info=info;
    }

    public void run(){
        boolean flag=false;
        for(int i=0;i<25;++i){
            if(flag){
                this.info.setName("Rollen");
                try{
                    Thread.sleep(100);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                this.info.setAge(20);
                flag=false;
            }else{
                this.info.setName("chunGe");
                try{
                    Thread.sleep(100);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                this.info.setAge(100);
                flag=true;
            }
        }
    }
}
/**
 * 消费者类
 * */
class Consumer implements Runnable{
    private Info info=null;
    public Consumer(Info info){
        this.info=info;
    }

    public void run(){
        for(int i=0;i<25;++i){
            try{
                Thread.sleep(100);
            }catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(this.info.getName()+"<---->"+this.info.getAge());
        }
    }
}

/**
 * 测试类
 * */
class hello13{
    public static void main(String[] args) {
        Info info=new Info();
        Producer pro=new Producer(info);
        Consumer con=new Consumer(info);
        new Thread(pro).start();
        new Thread(con).start();
    }
}