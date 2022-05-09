package com.blacktv.shejimoshi.创建型模式.抽象工厂模式;

/**
 * 抽象工厂测试类
 */
public class Test {
    public static void main(String[] args) throws Exception {
        BaseFactory ultraman = FactoryFactory.createFactory("ultraman");
        BaseFactory monster = FactoryFactory.createFactory("monster");
        ultraman.createUltraMan("ace").myPrint();
        monster.createMonster("baltan").print();
    }
}
