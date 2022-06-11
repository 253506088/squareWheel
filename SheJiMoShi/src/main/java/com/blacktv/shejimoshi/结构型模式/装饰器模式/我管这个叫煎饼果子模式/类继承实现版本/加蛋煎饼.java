package com.blacktv.shejimoshi.结构型模式.装饰器模式.我管这个叫煎饼果子模式.类继承实现版本;

public class 加蛋煎饼 extends 基础煎饼 {

    /**
     * 食材明细，打印出这套煎饼有啥食材
     */
    @Override
    public String detailedFoods() {
        return super.detailedFoods() + "、额外加一枚鸡蛋";
    }

    /**
     * 返回这套煎饼的价格
     *
     * @return
     */
    @Override
    public Double price() {
        return super.price() + 1.5;
    }
}
