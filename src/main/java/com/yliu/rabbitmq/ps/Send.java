package com.yliu.rabbitmq.ps;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yliu.rabbitmq.RabbitmqUtils;

public class Send {

    private static final String EXCHANGE_NAME = "PS_EXCHANGE";

    public static void main(String args[]) throws Exception {
        //获取连接
        Connection connection = RabbitmqUtils.getConnection();
        //获取channel
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");//分发
        //发送消息
        String msg = "hello exchange";
        System.out.println("[mq] send:" + msg);
        channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
        channel.close();
        connection.close();
    }
}
