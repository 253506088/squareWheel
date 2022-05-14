package com.blacktv.shejimoshi.创建型模式.建造者模式.food;

import com.blacktv.shejimoshi.创建型模式.建造者模式.base.Item;
import com.blacktv.shejimoshi.创建型模式.建造者模式.base.Packing;
import com.blacktv.shejimoshi.创建型模式.建造者模式.packing.Wrapper;

/**
 * 汉堡的抽象类，主要指定包装
 */
public abstract class Burger implements Item {
    /**
     * 汉堡肯定是用纸包起来的，所以这里写死用纸袋子
     *
     * @return
     */
    @Override
    public Packing packing() {
        return new Wrapper();
    }
}
