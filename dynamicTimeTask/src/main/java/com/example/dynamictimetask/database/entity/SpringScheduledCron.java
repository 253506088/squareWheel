package com.example.dynamictimetask.database.entity;

import com.baomidou.mybatisplus.annotation.TableName;


import com.example.dynamictimetask.database.base.BaseDO;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 定时任务配置表
 *
 * @author 黑白大彩电
 * @since 2022-04-08
 */
@Data
@TableName("spring_scheduled_cron")
public class SpringScheduledCron extends BaseDO<SpringScheduledCron> {

    private static final long serialVersionUID = 1L;

    /**
     * 逻辑id
     */
    @TableField("logic_id")
    private String logicId;

    /**
     * 定时任务完整类名
     */
    @TableField("cron_key")
    private String cronKey;

    /**
     * cron表达式
     */
    @TableField("cron_expression")
    private String cronExpression;

    /**
     * 任务名称
     */
    @TableField("task_name")
    private String taskName;

    /**
     * 任务描述
     */
    @TableField("task_explain")
    private String taskExplain;

    /**
     * 状态,1:正常;2:停用
     */
    @TableField("status")
    private String status;

    @Override
    protected Serializable pkVal() {
        return null;
    }


    public static void main(String[] args) {
        SpringScheduledCron p = new SpringScheduledCron();
        System.out.println(p.getClass());
        System.out.println(p.getClass().getName());
    }

}
