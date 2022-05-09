package com.blacktv.shejimoshi.创建型模式.工厂模式;

/**
 * 奥特曼工厂
 */
public class UltraManFactory {
    /**
     * 根据名称创建奥特曼
     *
     * @param name
     * @return
     */
    public static BaseUltraMan createUltraMan(String name) throws Exception {
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
}
