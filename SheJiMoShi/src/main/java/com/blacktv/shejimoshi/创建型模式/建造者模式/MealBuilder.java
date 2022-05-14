package com.blacktv.shejimoshi.创建型模式.建造者模式;

import com.blacktv.shejimoshi.创建型模式.建造者模式.drink.Coke;
import com.blacktv.shejimoshi.创建型模式.建造者模式.drink.Pepsi;
import com.blacktv.shejimoshi.创建型模式.建造者模式.food.ChickenBurger;
import com.blacktv.shejimoshi.创建型模式.建造者模式.food.VegBurger;

/**
 * 用于构建Meal，这里是建造者模式的核心
 */
public class MealBuilder {
    /**
     * 创建一顿，俩素汉堡+可口可乐
     *
     * @return
     */
    public static Meal prepareVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    /**
     * 创建一顿俩鸡肉汉堡+百事可乐
     *
     * @return
     */
    public static Meal prepareNonVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
