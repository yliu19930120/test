rabbitqmq的几种工作模式

1.简单模式，一个生产者一个消费者
2.工作模式，多个生产者多个消费者
3.发布订阅模式，一个发布者，多个订阅者、多个订阅者可同时消费同一条消息
4.路由模式，一个生产者可指定一个路由发送消息，多个消费者可接受指定路由的消息
一个消费者可订阅多个路由的消息
5.主题模式，与路由模式比较像，不过主题模式消费者可使用通配符订阅模糊的路由的消息
