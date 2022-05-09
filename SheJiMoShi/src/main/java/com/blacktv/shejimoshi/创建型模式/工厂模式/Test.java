package com.blacktv.shejimoshi.创建型模式.工厂模式;

public class Test {
    public static void main(String[] args) throws Exception {
        UltraManFactory.createUltraMan("ace").myPrint();
        UltraManFactory.createUltraMan("zoffy").myPrint();
        UltraManFactory.createUltraMan("jack").myPrint();
    }
}
