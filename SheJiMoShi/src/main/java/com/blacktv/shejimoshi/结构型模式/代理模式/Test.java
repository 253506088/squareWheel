package com.blacktv.shejimoshi.结构型模式.代理模式;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        //静态代理部分
        System.out.println(new 静态代理().returnString(13));
        System.out.println();

        //动态代理部分
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //创建被代理对象
        被代理目标 被代理目标 = new 被代理目标();
        //创建动态代理的模板
        动态代理 handler = new 动态代理(被代理目标);
        //生成代理类的对象，并且调用方法
        接口 proxy被代理目标 = (接口) Proxy.newProxyInstance(被代理目标.getClass().getClassLoader(), 被代理目标.getClass().getInterfaces(), handler);
        System.out.println(proxy被代理目标.returnString(13));
    }
}
