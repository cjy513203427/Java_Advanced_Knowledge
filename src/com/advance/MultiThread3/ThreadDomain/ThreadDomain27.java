package com.advance.MultiThread3.ThreadDomain;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/4 14:09
 * @Description:
 */
public class ThreadDomain27 {
    public static AtomicInteger aiRef = new AtomicInteger();

    public synchronized void addNum()
    {
        System.out.println(Thread.currentThread().getName() + "加了100之后的结果：" + aiRef.addAndGet(100));
        aiRef.getAndAdd(1);
    }
}