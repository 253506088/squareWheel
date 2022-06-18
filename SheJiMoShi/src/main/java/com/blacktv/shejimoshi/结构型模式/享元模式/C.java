package com.blacktv.shejimoshi.结构型模式.享元模式;

import java.util.UUID;

public class C {
    String uuid;

    public C() {
        this.uuid = UUID.randomUUID().toString();
    }
    public void say() {
        System.out.println("我是C类的实例，我的uuid：" + this.uuid);
    }
}
