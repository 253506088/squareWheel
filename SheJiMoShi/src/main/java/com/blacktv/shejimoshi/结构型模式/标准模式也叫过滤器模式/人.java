package com.blacktv.shejimoshi.结构型模式.标准模式也叫过滤器模式;

import lombok.Data;

@Data
public class 人 {
    private String 性别;
    private String 婚否;
    private String 名称;

    public 人(String 性别, String 婚否, String 名称) {
        this.性别 = 性别;
        this.婚否 = 婚否;
        this.名称 = 名称;
    }
}
