package com.blacktv.shejimoshi.创建型模式.建造者模式.food;

/**
 * 素汉堡，谁吃啊。。。
 */
public class VegBurger extends Burger {

    @Override
    public float price() {
        return 5.5f;
    }

    @Override
    public String name() {
        return "素汉堡，哇哦，没人吃";
    }
}