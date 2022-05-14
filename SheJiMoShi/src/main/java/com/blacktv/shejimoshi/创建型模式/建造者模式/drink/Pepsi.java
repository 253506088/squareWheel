package com.blacktv.shejimoshi.创建型模式.建造者模式.drink;

/**
 * 百事可乐
 */
public class Pepsi extends ColdDrink {

    @Override
    public float price() {
        return 3.5f;
    }

    @Override
    public String name() {
        return "百事可乐";
    }
}