package com.blacktv.shejimoshi.创建型模式.抽象工厂模式;

/**
 * 怪兽工厂
 */
public class MonsterFactory implements BaseFactory{
    /**
     * 根据名称创建怪兽
     *
     * @param name
     * @return
     */
    @Override
    public BaseMonster createMonster(String name) throws Exception {
        BaseMonster baseMonster = null;
        switch (name.toLowerCase()) {
            case "zetton":
                baseMonster = new ZettonMonster();
                break;
            case "baltan":
                baseMonster = new BaltanMonster();
                break;
            default:
                throw new Exception("未知的怪兽【" + name + "】");
        }
        return baseMonster;
    }

    /**
     * 根据名称创建奥特曼
     *
     * @param name
     * @return
     */
    @Override
    public BaseUltraMan createUltraMan(String name) throws Exception {
        return null;
    }
}
