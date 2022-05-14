package com.blacktv.shejimoshi.创建型模式.建造者模式.packing;

import com.blacktv.shejimoshi.创建型模式.建造者模式.base.Packing;

/**
 * 杯子
 */
public class Bottle implements Packing {
    /**
     * 包装名称
     *
     * @return
     */
    @Override
    public String pack() {
        return "杯子";
    }
}
