package com.blacktv.shejimoshi.结构型模式.外观模式;

/**
 * 外观模式的核心地方，将所有的东西汇总，提供给一个对外的接口.
 * 相当于控制器对外暴露API
 */
public class 医院接待员 {
    private 抓药 抓药 = new 抓药();
    private 科室检查 科室检查 = new 科室检查();
    private 门诊 门诊 = new 门诊();
    private 挂号 挂号 = new 挂号();

    void 挂号() {
        挂号.挂号();
    }

    void 门诊() {
        门诊.门诊();
    }

    void 科室检查() {
        科室检查.科室检查();
    }

    void 抓药() {
        抓药.抓药();
    }
}
