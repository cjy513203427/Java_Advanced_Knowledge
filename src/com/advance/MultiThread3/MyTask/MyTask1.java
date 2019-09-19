package com.advance.MultiThread3.MyTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: 谷天乐
 * @Date: 2019/9/18 21:17
 * @Description:
 */
public class MyTask1 extends TimerTask {
    private static Timer timer = new Timer();

    public void run()
    {
        System.out.println("运行了！时间为：" + new Date());
    }

    public static void main(String[] args) throws Exception
    {
        MyTask1 task = new MyTask1();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = "2019-9-18 21:22:00";
        Date dateRef = sdf.parse(dateString);
        System.out.println("字符串时间：" + dateRef.toLocaleString() + " 当前时间：" + new Date().toLocaleString());
        timer.schedule(task, dateRef);
    }
}