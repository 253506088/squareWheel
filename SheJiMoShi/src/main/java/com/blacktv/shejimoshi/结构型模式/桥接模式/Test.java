package com.blacktv.shejimoshi.结构型模式.桥接模式;

import com.blacktv.shejimoshi.结构型模式.桥接模式.发送消息服务.MessageService;
import com.blacktv.shejimoshi.结构型模式.桥接模式.发送消息服务.MessageServiceImpl;
import com.blacktv.shejimoshi.结构型模式.桥接模式.消息来源.BaseMessage;
import com.blacktv.shejimoshi.结构型模式.桥接模式.消息来源.SmsMessage;
import com.blacktv.shejimoshi.结构型模式.桥接模式.消息来源.WeChatMessage;
import com.blacktv.shejimoshi.结构型模式.桥接模式.消息队列实现.RabbitMqSendMessageService;
import com.blacktv.shejimoshi.结构型模式.桥接模式.消息队列实现.RedisSendMessageService;
import com.blacktv.shejimoshi.结构型模式.桥接模式.消息队列实现.SendMessageService;

import java.util.Date;

/**
 * 桥接模式案例测试入口
 */
public class Test {
    /**
     * 运行结果：
     * <p>
     * 开始发送消息...
     * 消息来源于SMS短信，数据已整合，发送者id：黑白大彩电001 发送时间：2022-05-21 16:49:58
     * 由RabbitMq实现的消息队列处理本消息，消息内容：
     * 大彩电在测试桥接模式
     * 发送消息完毕...
     * <p>
     * 开始发送消息...
     * 消息来源于微信WeChat，数据已整合，发送者id：呱呱呱想吃天鹅肉002 发送时间：2022-05-21 16:49:58
     * 由RabbitMq实现的消息队列处理本消息，消息内容：
     * 呱呱呱在测试桥接模式
     * 发送消息完毕...
     * <p>
     * 开始发送消息...
     * 消息来源于SMS短信，数据已整合，发送者id：黑白大彩电001 发送时间：2022-05-21 16:49:58
     * 由redis实现的消息队列处理本消息，消息内容：
     * 大彩电在测试桥接模式
     * 发送消息完毕...
     * <p>
     * 开始发送消息...
     * 消息来源于微信WeChat，数据已整合，发送者id：呱呱呱想吃天鹅肉002 发送时间：2022-05-21 16:49:58
     * 由redis实现的消息队列处理本消息，消息内容：
     * 呱呱呱在测试桥接模式
     * 发送消息完毕...
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //创建整合类对象
        MessageService messageService = new MessageServiceImpl();
        //创建两种消息队列实现类的对象
        SendMessageService rabbitMq = new RabbitMqSendMessageService();
        SendMessageService redis = new RedisSendMessageService();
        //创建两种不同的消息来源
        BaseMessage smsMessage = new SmsMessage("黑白大彩电001", new Date(), "大彩电在测试桥接模式");
        BaseMessage weChatMessage = new WeChatMessage("呱呱呱想吃天鹅肉002", new Date(), "呱呱呱在测试桥接模式");

        //开始发送数据
        messageService.setSendMessageService(rabbitMq);
        messageService.send(smsMessage);
        messageService.send(weChatMessage);

        messageService.setSendMessageService(redis);
        messageService.send(smsMessage);
        messageService.send(weChatMessage);
    }
}
