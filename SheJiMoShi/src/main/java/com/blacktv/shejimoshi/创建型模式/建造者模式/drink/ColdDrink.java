package com.blacktv.shejimoshi.创建型模式.建造者模式.drink;

import com.blacktv.shejimoshi.创建型模式.建造者模式.base.Item;
import com.blacktv.shejimoshi.创建型模式.建造者模式.base.Packing;
import com.blacktv.shejimoshi.创建型模式.建造者模式.packing.Bottle;

/**
 * 冷饮的抽象类，主要指定包装
 */
public abstract class ColdDrink implements Item {
    /**
     * 冷饮用杯子装，所以这里写死用杯子
     *
     * @return
     */
    @Override
    public Packing packing() {
        return new Bottle();
    }
}