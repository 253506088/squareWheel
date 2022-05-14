package com.blacktv.shejimoshi.创建型模式.建造者模式.base;

/**
 * 食物必须实现的接口
 */
public interface Item {
    /**
     * 食品名称
     * @return
     */
    String name();

    /**
     * 使用的包装
     * @return
     */
    Packing packing();

    /**
     * 价格
     * @return
     */
    float price();
}
