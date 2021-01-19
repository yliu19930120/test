package com.yliu.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.yliu.rabbitmq.RabbitmqUtils;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 简单模式的发送者
 */
public class Send {

    private final static String QUEUE_NAME = "hello";
    public static void main(String[] argv) throws Exception {

        while (true){
            Scanner sc = new Scanner(System.in);
            try (Connection connection = RabbitmqUtils.getConnection();
                 Channel channel = connection.createChannel()) {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                String message = sc.nextLine();
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + message + "'");
            }
        }

    }
}
