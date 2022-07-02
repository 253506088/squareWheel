package com.blacktv.shejimoshi.结构型模式.代理模式;

/**
 * 静态代理实现，静态代理都比较局限，只能代理代码里写死的代理目标。
 */
public class 静态代理 {
    private 被代理目标 被代理目标 = new 被代理目标();
    public String returnString(Integer number) {
        System.out.println("静态代理——执行目标功能之前");
        String result = 被代理目标.returnString(number);
        System.out.println("静态代理——执行目标功能之后");
        return result;
    }
}
