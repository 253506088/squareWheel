package com.blacktv.shejimoshi.创建型模式.抽象工厂模式;

/**
 * 杰顿怪兽
 */
public class ZettonMonster implements BaseMonster{
    /**
     * 输出怪兽名字
     */
    @Override
    public void print() {
        System.out.println("我是杰顿，杀死了初代奥特曼");
    }
}
