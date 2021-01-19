package com.yliu.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yliu.rabbitmq.RabbitmqUtils;

public class Send {
    private static final String QUEUE_NAME = "WORK_QUEUE";

    public static void main(String args[]) throws Exception {
        //获取连接
        Connection connection = RabbitmqUtils.getConnection();
        //获取channel
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        for (int i = 0; i < 50; i++) {
            String msg = "hello " + i;
            System.out.println("[mq] send:" + msg);
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            Thread.sleep(i * 20);
        }
        channel.close();
        connection.close();
    }
}
