package com.blacktv.shejimoshi.结构型模式.享元模式;

import java.util.UUID;

public class A {
    String uuid;

    public A() {
        this.uuid = UUID.randomUUID().toString();
    }

    public void say() {
        System.out.println("我是A类的实例，我的uuid：" + this.uuid);
    }
}
