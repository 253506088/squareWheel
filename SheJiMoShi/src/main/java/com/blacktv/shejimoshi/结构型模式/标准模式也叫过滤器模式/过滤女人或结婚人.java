package com.blacktv.shejimoshi.结构型模式.标准模式也叫过滤器模式;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 过滤女人或结婚人 implements 标准接口 {
    private 标准接口 过滤结婚人 = new 过滤结婚人();
    private 标准接口 过滤女人 = new 过滤女人();

    @Override
    public List<人> 过滤符合条件的人(List<人> list) {
        if (list == null || list.size() == 0) {
            return list;
        }
        return this.addAll(过滤女人.过滤符合条件的人(list), 过滤结婚人.过滤符合条件的人(list));
    }

    /**
     * 合并两个列表，并且驱逐重复项
     *
     * @param a
     * @param b
     * @return
     */
    private List<人> addAll(List<人> a, List<人> b) {
        Set<人> 人set = new HashSet<>();
        a.forEach(e -> {
            人set.add(e);
        });
        b.forEach(e -> {
            人set.add(e);
        });
        List list = Arrays.asList(人set.toArray());
        return list;
    }
}