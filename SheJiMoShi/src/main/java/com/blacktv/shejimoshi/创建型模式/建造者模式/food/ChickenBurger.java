package com.blacktv.shejimoshi.创建型模式.建造者模式.food;

/**
 * 鸡肉汉堡，廉价的快乐
 */
public class ChickenBurger extends Burger {

    @Override
    public float price() {
        return 9.5f;
    }

    @Override
    public String name() {
        return "鸡肉汉堡，廉价的快乐";
    }
}