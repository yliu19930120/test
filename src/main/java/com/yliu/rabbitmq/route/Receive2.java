package com.yliu.rabbitmq.route;

import com.rabbitmq.client.*;
import com.yliu.rabbitmq.RabbitmqUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Receive2 {

    public static final String QUEUE_NAME = "ROUTE_QUEUE_1";
    public static final String EXCHANGE_NAME = "ROUTE_EXCHANGE";
    public static final String ROUTING_KEY_world = "ROUTE_1_WORLD";
    public static final String ROUTING_KEY_hello = "ROUTE_1_HELLO";

    public static void main(String args[]) throws Exception {
        //获取连接
        Connection connection = RabbitmqUtils.getConnection();
        //获取channel
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //设置预读取数
        channel.basicQos(1);
        //绑定交换机和路由器，可以绑定多个路由
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY_world);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY_hello);
        //定义消息消费者
        Consumer consumer = new DefaultConsumer(channel) {
            /**
             * No-op implementation of {@link Consumer#handleDelivery}.
             *
             * @param consumerTag
             * @param envelope
             * @param properties
             * @param body
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println(envelope.getRoutingKey() + " [2] Receive1 msg:" + msg);
            }
        };
        //接收消息
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
