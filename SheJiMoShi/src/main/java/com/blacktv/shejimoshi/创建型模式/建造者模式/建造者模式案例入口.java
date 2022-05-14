package com.blacktv.shejimoshi.创建型模式.建造者模式;

public class 建造者模式案例入口 {
    public static void main(String[] args) {
        Meal prepareVegMeal = MealBuilder.prepareVegMeal();
        System.out.println("素食主义者的一餐，价格"+prepareVegMeal.getCost());
        prepareVegMeal.showItems();

        Meal prepareNonVegMeal = MealBuilder.prepareNonVegMeal();
        System.out.println("\n正常人的一餐，价格"+prepareNonVegMeal.getCost());
        prepareNonVegMeal.showItems();
    }
}
