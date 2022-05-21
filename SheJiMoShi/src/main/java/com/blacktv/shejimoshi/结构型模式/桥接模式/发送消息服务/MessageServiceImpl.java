package com.blacktv.shejimoshi.结构型模式.桥接模式.发送消息服务;

import com.blacktv.shejimoshi.结构型模式.桥接模式.消息来源.BaseMessage;
import com.blacktv.shejimoshi.结构型模式.桥接模式.消息队列实现.SendMessageService;

/**
 * 发送消息服务，这主要是一个中间整合的地方
 */
public class MessageServiceImpl extends BaseMessageService {
    /**
     * 为本服务设置消息队列的实现方式
     *
     * @param sendMessageService
     */
    @Override
    public void setSendMessageService(SendMessageService sendMessageService) {
        super.sendMessageService = sendMessageService;
    }

    /**
     * 发送消息，这里不会自己实现一个发送消息的方法，而是调用【setSendMessageService()】方法设置进来的消息队列实现类的【send()】方法
     *
     * @param message
     */
    @Override
    public void send(BaseMessage message) throws Exception {
        if (super.sendMessageService == null) {
            throw new Exception("未指定具体消息队列需实现类");
        }
        super.sendMessageService.send(message);
    }
}
