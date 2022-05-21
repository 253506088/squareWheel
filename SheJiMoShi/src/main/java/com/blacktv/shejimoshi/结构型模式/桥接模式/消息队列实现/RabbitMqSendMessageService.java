package com.blacktv.shejimoshi.结构型模式.桥接模式.消息队列实现;

import com.blacktv.shejimoshi.结构型模式.桥接模式.消息来源.BaseMessage;

/**
 * RabbitMq消息队列发送消息
 */
public class RabbitMqSendMessageService implements SendMessageService {
    /**
     * 发送消息
     *
     * @param message
     */
    @Override
    public void send(BaseMessage message) {
        System.out.println("开始发送消息...");
        String data = message.combineData().toString();
        System.out.println("由RabbitMq实现的消息队列处理本消息，消息内容：\n" + data);
        System.out.println("发送消息完毕...\n");
    }
}
