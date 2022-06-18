package com.blacktv.shejimoshi.结构型模式.享元模式;

import java.util.TreeMap;

public class 享元模式存储 {
    public static final TreeMap<String, Object> map = new TreeMap<>();

    /**
     * 享元模式模拟入口
     *
     * @param beanName
     * @return
     * @throws Exception
     */
    public static Object getBean(String beanName) throws Exception {
        Object bean = map.get(beanName);
        if (bean == null) {
            switch (beanName) {
                case "A":
                    bean = new A();
                    break;
                case "B":
                    bean = new B();
                    break;
                case "C":
                    bean = new C();
                    break;
                default:
                    throw new Exception("未识别的bean类型：" + beanName);
            }
            map.put(beanName, bean);
        }
        return bean;
    }
}
