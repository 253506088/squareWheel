package com.blacktv.shejimoshi.结构型模式.桥接模式.发送消息服务;

import com.blacktv.shejimoshi.结构型模式.桥接模式.消息来源.BaseMessage;
import com.blacktv.shejimoshi.结构型模式.桥接模式.消息队列实现.SendMessageService;

/**
 * 消息队列服务的接口
 */
public interface MessageService {
    /**
     * 为本服务设置消息队列的实现方式
     *
     * @param sendMessageService
     */
    void setSendMessageService(SendMessageService sendMessageService);

    /**
     * 发送消息，这里不会自己实现一个发送消息的方法，而是调用【setSendMessageService()】方法设置进来的消息队列实现类的【send()】方法
     *
     * @param message
     */
    void send(BaseMessage message) throws Exception;
}
