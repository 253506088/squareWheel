package com.blacktv.shejimoshi.结构型模式.标准模式也叫过滤器模式;

import java.util.ArrayList;
import java.util.List;

public class 过滤结婚人 implements 标准接口 {
    @Override
    public List<人> 过滤符合条件的人(List<人> list) {
        if (list == null || list.size() == 0) {
            return list;
        }
        List<人> result = new ArrayList<>();
        list.forEach(e->{
            if("已婚".equals(e.get婚否())){
                result.add(e);
            }
        });
        return result;
    }
}