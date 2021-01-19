package com.yliu.rabbitmq.work;

import com.rabbitmq.client.*;
import com.yliu.rabbitmq.RabbitmqUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Receive2 {

    private static final String QUEUE_NAME = "WORK_QUEUE";

    public static void main(String args[]) throws Exception {
        //获取连接
        Connection connection = RabbitmqUtils.getConnection();
        //获取channel
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //定义一个消费这
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException, UnsupportedEncodingException {
                String msg = new String(body, "utf-8");
                System.out.println("[2] Receive2 msg:" + msg);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("[2] done");
                }
            }
        };
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }
}
