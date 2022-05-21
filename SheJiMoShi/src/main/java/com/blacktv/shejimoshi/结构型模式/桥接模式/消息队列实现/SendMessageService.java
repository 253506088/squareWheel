package com.blacktv.shejimoshi.结构型模式.桥接模式.消息队列实现;

import com.blacktv.shejimoshi.结构型模式.桥接模式.消息来源.BaseMessage;

/**
 * 消息队列接口，用于规定本项目中所有消息队列需要实现的方法
 */
public interface SendMessageService {
    /**
     * 发送消息
     *
     * @param message
     */
    void send(BaseMessage message);
}
