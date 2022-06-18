package com.blacktv.shejimoshi.结构型模式.享元模式;

public class Test {
    public static void main(String[] args) throws Exception {
        A a1 = (A)享元模式存储.getBean("A");
        A a2 = (A)享元模式存储.getBean("A");
        A a3 = (A)享元模式存储.getBean("A");
        a1.say();
        a2.say();
        a3.say();
        System.out.println();

        B b1 = (B)享元模式存储.getBean("B");
        B b2 = (B)享元模式存储.getBean("B");
        B b3 = (B)享元模式存储.getBean("B");
        b1.say();
        b2.say();
        b3.say();
        System.out.println();

        C c1 = (C)享元模式存储.getBean("C");
        C c2 = (C)享元模式存储.getBean("C");
        C c3 = (C)享元模式存储.getBean("C");

        c1.say();
        c2.say();
        c3.say();
    }
}
