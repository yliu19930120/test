package com.yliu.rabbitmq.route;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yliu.rabbitmq.RabbitmqUtils;

public class Send {

    public static final String EXCHANGE_NAME = "ROUTE_EXCHANGE";
    public static final String ROUTING_KEY = "ROUTE_1_HELLO";

    public static void main(String args[]) throws Exception {
        //获取连接
        Connection connection = RabbitmqUtils.getConnection();
        //获取channel
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String msg = "route message ->" + ROUTING_KEY;
        System.out.println("对 " + ROUTING_KEY + " 发送消息：" + msg);
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, msg.getBytes());
        //关闭连接
        channel.close();
        connection.close();
    }
}
