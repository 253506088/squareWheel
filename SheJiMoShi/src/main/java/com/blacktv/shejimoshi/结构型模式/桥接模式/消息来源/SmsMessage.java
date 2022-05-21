package com.blacktv.shejimoshi.结构型模式.桥接模式.消息来源;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * 消息来源短信SMS
 */
public class SmsMessage extends BaseMessage {
    public SmsMessage(String sendUserId, Date sendDate, Object data) {
        super(sendUserId, sendDate, data);
    }

    /**
     * 整合数据，为发送消息方法提供
     *
     * @return
     */
    @Override
    public Object combineData() {
        System.out.println("消息来源于SMS短信，数据已整合，发送者id：" + getSendUserId() + " 发送时间：" + DateUtil.format(getSendDate(), "yyyy-MM-dd HH:mm:ss"));
        return this.getData();
    }
}
