package com.blacktv.shejimoshi.创建型模式.建造者模式.drink;

/**
 * 可口可乐
 */
public class Coke extends ColdDrink {

    @Override
    public float price() {
        return 3.5f;
    }

    @Override
    public String name() {
        return "可口可乐";
    }
}