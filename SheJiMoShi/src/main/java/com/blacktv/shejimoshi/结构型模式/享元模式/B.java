package com.blacktv.shejimoshi.结构型模式.享元模式;

import java.util.UUID;

public class B {
    String uuid;

    public B() {
        this.uuid = UUID.randomUUID().toString();
    }

    public void say() {
        System.out.println("我是B类的实例，我的uuid：" + this.uuid);
    }
}
