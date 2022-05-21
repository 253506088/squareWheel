package com.blacktv.shejimoshi.结构型模式.桥接模式.消息来源;

import lombok.Data;

import java.util.Date;

/**
 * 消息来源实现类的接口
 */
@Data
public class BaseMessage {
    /**
     * 发送者id
     */
    private String sendUserId;
    /**
     * 发送时间
     */
    private Date sendDate;
    /**
     * 要发送的消息数据
     */
    private Object data;

    /**
     * 整合数据，为发送消息方法提供
     *
     * @return
     */
    public Object combineData() {
        return this.data;
    }

    public BaseMessage(String sendUserId, Date sendDate, Object data) {
        this.sendUserId = sendUserId;
        this.sendDate = sendDate;
        this.data = data;
    }
}
