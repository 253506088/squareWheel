package com.blacktv.shejimoshi.结构型模式.标准模式也叫过滤器模式;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<人> list = new ArrayList<>();
        list.add(new 人("男", "已婚", "张1"));
        list.add(new 人("男", "单身", "张2"));
        list.add(new 人("男", "单身", "张3"));
        list.add(new 人("男", "已婚", "张4"));

        list.add(new 人("女", "已婚", "李1"));
        list.add(new 人("女", "单身", "李2"));
        list.add(new 人("女", "单身", "李3"));
        list.add(new 人("女", "已婚", "李4"));

        标准接口 过滤单身人 = new 过滤单身人();
        标准接口 过滤男人 = new 过滤男人();
        标准接口 过滤结婚人 = new 过滤结婚人();
        标准接口 过滤女人 = new 过滤女人();
        标准接口 过滤男人并单身人 = new 过滤男人并单身人();
        标准接口 过滤女人或结婚人 = new 过滤女人或结婚人();

        System.out.println("过滤单身人");
        System.out.println(过滤单身人.过滤符合条件的人(list));

        System.out.println("\n过滤男人");
        System.out.println(过滤男人.过滤符合条件的人(list));

        System.out.println("\n过滤结婚人");
        System.out.println(过滤结婚人.过滤符合条件的人(list));

        System.out.println("\n过滤女人");
        System.out.println(过滤女人.过滤符合条件的人(list));

        System.out.println("\n过滤男人并单身人");
        System.out.println(过滤男人并单身人.过滤符合条件的人(list));

        System.out.println("\n过滤女人或结婚人");
        System.out.println(过滤女人或结婚人.过滤符合条件的人(list));
    }
}
