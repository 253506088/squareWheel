package com.blacktv.shejimoshi.结构型模式.代理模式;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class 动态代理 implements InvocationHandler {
    //被代理的类
    private Object target;

    /**
     * 这里可以代理所有的类型
     *
     * @param target
     */
    public 动态代理(Object target) {
        this.target = target;
    }

    /**
     * 代理程序
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理——执行目标功能之前");
        System.out.println("动态代理——动态代理执行的目标方法：" + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("动态代理——执行目标功能之后");
        return result;
    }
}