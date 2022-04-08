package com.example.dynamictimetask.database.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dynamictimetask.database.entity.SpringScheduledCron;
import com.example.dynamictimetask.database.mapper.SpringScheduledCronMapper;
import com.example.dynamictimetask.database.service.ISpringScheduledCronService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 定时任务配置表
 *
 * @author 黑白大彩电
 * @since 2022-04-08
 */
@Service
public class SpringScheduledCronServiceImpl extends ServiceImpl<SpringScheduledCronMapper, SpringScheduledCron> implements ISpringScheduledCronService {

    /**
     * 根据cronKey获取
     *
     * @param cronKey
     * @return
     */
    @Override
    public SpringScheduledCron findByCronKey(String cronKey) {
        SpringScheduledCron springScheduledCron = this.baseMapper.selectOne(new QueryWrapper<SpringScheduledCron>().eq("cron_key", cronKey));
        return springScheduledCron;
    }
}
