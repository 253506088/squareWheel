package com.blacktv.shejimoshi.结构型模式.装饰器模式.我管这个叫煎饼果子模式.类继承实现版本;

public class Test {
    public static void main(String[] args) {
        加蛋加肠加辣条煎饼 加蛋加肠加辣条煎饼 = new 加蛋加肠加辣条煎饼();
        加蛋加肠煎饼 加蛋加肠煎饼 = new 加蛋加肠煎饼();
        加蛋煎饼 加蛋煎饼 = new 加蛋煎饼();
        基础煎饼 基础煎饼 = new 基础煎饼();

        System.out.println("加蛋加肠加辣条煎饼-start");
        System.out.println(加蛋加肠加辣条煎饼.detailedFoods() + 加蛋加肠加辣条煎饼.price() + "元");
        System.out.println("加蛋加肠加辣条煎饼-end");

        System.out.println("\n加蛋加肠煎饼-start");
        System.out.println(加蛋加肠煎饼.detailedFoods() + 加蛋加肠煎饼.price() + "元");
        System.out.println("加蛋加肠煎饼-end");

        System.out.println("\n加蛋煎饼-start");
        System.out.println(加蛋煎饼.detailedFoods() + 加蛋煎饼.price() + "元");
        System.out.println("加蛋煎饼-end");

        System.out.println("\n基础煎饼-start");
        System.out.println(基础煎饼.detailedFoods() + 基础煎饼.price() + "元");
        System.out.println("基础煎饼-end");
    }
}
