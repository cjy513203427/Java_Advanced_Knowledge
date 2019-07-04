package com.advance.MultiThread3;

import java.util.Date;

/**
 * @Author: 谷天乐
 * @Date: 2019/7/2 15:02
 * @Description:
 */
public class MyObject {
        public synchronized void speedPrintString()
        {
            System.out.println("speedPrintString__getLock time = " +
                    new Date() + ", run ThreadName = " +
                    Thread.currentThread().getName());
            System.out.println("speedPrintString__releaseLock time = " +
                    new Date() + ", run ThreadName = " +
                    Thread.currentThread().getName());
        }
}