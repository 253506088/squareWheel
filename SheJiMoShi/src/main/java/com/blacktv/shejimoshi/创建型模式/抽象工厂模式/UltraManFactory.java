package com.blacktv.shejimoshi.创建型模式.抽象工厂模式;

/**
 * 奥特曼工厂
 */
public class UltraManFactory implements BaseFactory {
    /**
     * 根据名称创建奥特曼
     *
     * @param name
     * @return
     */
    @Override
    public BaseUltraMan createUltraMan(String name) throws Exception {
        BaseUltraMan baseUltraMan = null;
        switch (name.toLowerCase()) {
            case "jack":
                baseUltraMan = new JackUltraMan();
                break;
            case "ace":
                baseUltraMan = new AceUltraMan();
                break;
            case "zoffy":
                baseUltraMan = new ZoffyUltraMan();
                break;
            default:
                throw new Exception("未知的奥特曼【" + name + "】,去问问光之国吧");
        }
        return baseUltraMan;
    }

    /**
     * 根据名称创建怪兽
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public BaseMonster createMonster(String name) throws Exception {
        return null;
    }
}
