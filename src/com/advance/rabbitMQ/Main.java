package com.advance.rabbitMQ;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class Main {

    public Main() throws Exception
    {

        // 创建消费者，即消息接收者，并启动线程
        QueueConsumer consumer = new QueueConsumer("queue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        // 创建生产者，即消息发送者
        Producer producer = new Producer("queue");

        // 循环发送消息
        for (int i = 0; i < 20; i++)
        {
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message Number " + i + " sent.");
        }
    }

    /**
     * @param args
     * @throws SQLException
     * @throws IOException
     */
    public static void main(String[] args) throws Exception
    {
        new Main();
    }
}