package com.blacktv.shejimoshi.创建型模式.抽象工厂模式;

/**
 * 巴尔坦星人
 */
public class BaltanMonster implements BaseMonster{
    /**
     * 输出怪兽名字
     */
    @Override
    public void print() {
        System.out.println("我是巴尔坦星人，小龙虾");
    }
}
