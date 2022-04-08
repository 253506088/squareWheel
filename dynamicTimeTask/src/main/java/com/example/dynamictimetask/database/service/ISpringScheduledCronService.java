package com.example.dynamictimetask.database.service;

import com.example.dynamictimetask.database.entity.SpringScheduledCron;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 定时任务配置表
 *
 * @author 黑白大彩电
 * @since 2022-04-08
 */
public interface ISpringScheduledCronService extends IService<SpringScheduledCron> {
    /**
     * 根据cronKey获取
     *
     * @param cronKey
     * @return
     */
    SpringScheduledCron findByCronKey(String cronKey);


}
