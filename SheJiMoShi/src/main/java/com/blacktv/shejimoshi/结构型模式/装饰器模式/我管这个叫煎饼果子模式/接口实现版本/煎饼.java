package com.blacktv.shejimoshi.结构型模式.装饰器模式.我管这个叫煎饼果子模式.接口实现版本;

/**
 * 煎饼的接口
 */
public interface 煎饼 {
    /**
     * 食材明细，打印出这套煎饼有啥食材
     */
    String detailedFoods();

    /**
     * 返回这套煎饼的价格
     *
     * @return
     */
    Double price();
}
