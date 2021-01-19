package com.yliu.rabbitmq.subject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yliu.rabbitmq.RabbitmqUtils;

public class Send {

    public static final String EXCHANGE_NAME = "SUBJECT_EXCHANGE";
    public static final String ROUTING_KEY = "SUBJECT_1.add";

    public static void main(String args[]) throws Exception {
        //获取连接
        Connection connection = RabbitmqUtils.getConnection();
        //获取channel
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
//        String routingKey = ROUTING_KEY + ".add";
        String routingKey = ROUTING_KEY + ".publish";
//        String routingKey = ROUTING_KEY + ".update";

        String msg = "route message ->" + routingKey;
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, msg.getBytes());
        System.out.println("对 " + routingKey + " 发送消息：" + msg);
        //关闭连接
        channel.close();
        connection.close();
    }
}
