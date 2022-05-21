package com.blacktv.shejimoshi.结构型模式.桥接模式.发送消息服务;

import com.blacktv.shejimoshi.结构型模式.桥接模式.消息队列实现.SendMessageService;

/**
 * 最基本的发送消息服务对象，所有的实现都要继承本类
 */
public abstract class BaseMessageService implements MessageService {
    SendMessageService sendMessageService;
}
