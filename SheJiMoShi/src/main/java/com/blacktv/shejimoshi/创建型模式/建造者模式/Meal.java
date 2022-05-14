package com.blacktv.shejimoshi.创建型模式.建造者模式;

import com.blacktv.shejimoshi.创建型模式.建造者模式.base.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * 一顿饭吃什么
 */
public class Meal {
    private List<Item> items = new ArrayList<>();

    /**
     * 点餐？
     *
     * @param item
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * 一顿饭吃了多少钱
     *
     * @return
     */
    public float getCost() {
        float cost = 0.0f;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    /**
     * 一顿饭吃了什么
     */
    public void showItems() {
        for (Item item : items) {
            System.out.print("Item : " + item.name());
            System.out.print(", Packing : " + item.packing().pack());
            System.out.println(", Price : " + item.price());
        }
    }
}
