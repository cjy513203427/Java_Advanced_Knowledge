package com.advance.MultiThread3.MyTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: 谷天乐
 * @Date: 2019/9/19 21:02
 * @Description:
 */
public class MyTask2 extends TimerTask {
    private static Timer timer = new Timer();

    public void run()
    {
        System.out.println("运行了！时间为：" + new Date());
    }

    public static void main(String[] args) throws Exception
    {
        MyTask2 task1 = new MyTask2();
        MyTask2 task2 = new MyTask2();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString1 = "2019-9-19 21:12:00";
        String dateString2 = "2019-9-19 21:12:00";
        Date dateRef1 = sdf1.parse(dateString1);
        Date dateRef2 = sdf2.parse(dateString2);
        System.out.println("字符串时间：" + dateRef1.toLocaleString() + " 当前时间：" + new Date().toLocaleString());
        System.out.println("字符串时间：" + dateRef2.toLocaleString() + " 当前时间：" + new Date().toLocaleString());
        timer.schedule(task1, dateRef1);
        timer.schedule(task2, dateRef2);
    }
}