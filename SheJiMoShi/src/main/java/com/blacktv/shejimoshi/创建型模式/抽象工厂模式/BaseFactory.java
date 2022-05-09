package com.blacktv.shejimoshi.创建型模式.抽象工厂模式;


/**
 * 工厂接口
 */
public interface BaseFactory {
    /**
     * 根据名称创建怪兽
     *
     * @param name
     * @return
     * @throws Exception
     */
    public BaseMonster createMonster(String name) throws Exception;

    /**
     * 根据名称创建奥特曼
     *
     * @param name
     * @return
     */
    public BaseUltraMan createUltraMan(String name) throws Exception;
}
