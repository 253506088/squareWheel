package com.blacktv.shejimoshi.结构型模式.标准模式也叫过滤器模式;

import java.util.List;

public class 过滤男人并单身人 implements 标准接口 {
    private 标准接口 过滤单身人 = new 过滤单身人();
    private 标准接口 过滤男人 = new 过滤男人();
    @Override
    public List<人> 过滤符合条件的人(List<人> list) {
        if (list == null || list.size() == 0) {
            return list;
        }
        List<人> buff = 过滤单身人.过滤符合条件的人(list);
        List<人> result = 过滤男人.过滤符合条件的人(buff);
        return result;
    }
}