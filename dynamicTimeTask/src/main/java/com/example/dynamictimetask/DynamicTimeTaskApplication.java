package com.example.dynamictimetask;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

//开启定时任务
@EnableScheduling
@MapperScan({"com.example.dynamictimetask.**.mapper"})
@ComponentScan(basePackages={"com.example.dynamictimetask.*.**.**"})
@SpringBootApplication
public class DynamicTimeTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicTimeTaskApplication.class, args);
    }

}
