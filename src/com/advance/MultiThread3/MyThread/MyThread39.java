package com.advance.MultiThread3.MyThread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/22 14:49
 * @Description:
 */
public class MyThread39 {
    public static void main(String[] args) {

        System.out.println("开始");
        final Lock lock = new ReentrantLock();
        new Thread() {
            @Override
            public void run() {
                String tName = Thread.currentThread().getName();
                if (lock.tryLock()) {
                    System.out.println(tName + "获取到锁！");
                } else {
                    System.out.println(tName + "获取不到锁！");
                    return;
                }
                try {
                    for (int i = 0; i < 5; i++) {
                        System.out.println(tName + ":" + i);
                    }
                    Thread.sleep(5000);
                } catch (Exception e) {
                    System.out.println(tName + "出错了！");
                } finally {
                    System.out.println(tName + "释放锁！");
                    lock.unlock();
                }

            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                String tName = Thread.currentThread().getName();

                try {
                    if (lock.tryLock(1,TimeUnit.SECONDS)) {
                        System.out.println(tName + "获取到锁！");
                    } else {
                        System.out.println(tName + "获取不到锁！");
                        return;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    for (int i = 0; i < 5; i++) {
                        System.out.println(tName + ":" + i);
                    }

                } catch (Exception e) {
                    System.out.println(tName + "出错");
                } finally {
                    System.out.println(tName + "释放锁！");
                    lock.unlock();
                }
            }
        }.start();

        System.out.println("结束");
    }
}