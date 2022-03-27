package com.example.myhashmap;

import com.alibaba.fastjson.JSONArray;
import com.example.myhashmap.map.jdk7Version.MyHashMapJDK7;

import java.util.HashMap;

public class Test {
    public static void main(String[] args) throws Exception {
        MyHashMapJDK7<Integer, String> myHashMapJDK7 = new MyHashMapJDK7<>(4);
        myHashMapJDK7.put(null,"null测试");
        System.out.println(myHashMapJDK7.get(null));
        for (int i = 0; i < 10; i++) {
            myHashMapJDK7.put(i, "黑白大彩电"+i);
        }
        System.out.println(myHashMapJDK7.get(8));
        myHashMapJDK7.put(8,"niubi");
        System.out.println(myHashMapJDK7.get(8));
        myHashMapJDK7.remove(8);
        System.out.println(myHashMapJDK7.get(8));
        for(Integer key : myHashMapJDK7.keySet()){
            System.out.println("遍历："+myHashMapJDK7.get(key));
        }
        System.out.println(JSONArray.toJSONString(myHashMapJDK7));
    }

}

