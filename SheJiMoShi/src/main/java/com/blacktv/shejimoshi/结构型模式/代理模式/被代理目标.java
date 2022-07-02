package com.blacktv.shejimoshi.结构型模式.代理模式;

/**
 * 这是被代理的目标
 */
public class 被代理目标 implements 接口{
    @Override
    public String returnString(Integer number) {
        return number != null ? number.toString() : "";
    }
}
