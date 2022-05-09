package com.blacktv.shejimoshi.创建型模式.抽象工厂模式;

import com.blacktv.shejimoshi.创建型模式.工厂模式.AceUltraMan;
import com.blacktv.shejimoshi.创建型模式.工厂模式.BaseUltraMan;
import com.blacktv.shejimoshi.创建型模式.工厂模式.JackUltraMan;
import com.blacktv.shejimoshi.创建型模式.工厂模式.ZoffyUltraMan;

/**
 * 工厂的工厂
 */
public class FactoryFactory {
    /**
     * 根据名称获取工厂
     *
     * @param name
     * @return
     * @throws Exception
     */
    public static BaseFactory createFactory(String name) throws Exception {
        BaseFactory baseFactory = null;
        switch (name.toLowerCase()) {
            case "ultraman":
                baseFactory = new UltraManFactory();
                break;
            case "monster":
                baseFactory = new MonsterFactory();
                break;
            default:
                throw new Exception("未知的工厂【" + name + "】");
        }
        return baseFactory;
    }
}
