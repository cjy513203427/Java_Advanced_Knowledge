package com.advance.concurrent_programming;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * @Author: 谷天乐
 * @Date: 2019/3/6 17:36
 * @Description:
 */
public class Calculator implements Runnable {
    private Logger logger = LogManager.getLogger(Calculator.class);
    private int number;

    public Calculator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i=0;i<=10;i++){
            System.out.printf("%s::%d*%d=%d\n",Thread.currentThread().getName(),number,i,i*number);
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<=10;i++){
            Calculator calculator = new Calculator(i);
            Thread thread = new Thread(calculator);
            thread.start();
        }
    }
}