package com.blacktv.shejimoshi.结构型模式.外观模式;

public class Test {
    public static void main(String[] args) {
        医院接待员 医院接待员 = new 医院接待员();
        医院接待员.挂号();
        医院接待员.门诊();
        医院接待员.科室检查();
        医院接待员.抓药();
    }
}
