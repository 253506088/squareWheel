/*Table structure for table `spring_scheduled_cron` */

DROP TABLE IF EXISTS `spring_scheduled_cron`;

CREATE TABLE `spring_scheduled_cron` (
                                         `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
                                         `logic_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '逻辑id',
                                         `cron_key` varchar(256) CHARACTER SET utf8 DEFAULT NULL COMMENT '定时任务完整类名',
                                         `cron_expression` varchar(256) CHARACTER SET utf8 DEFAULT NULL COMMENT 'cron表达式',
                                         `task_name` varchar(256) CHARACTER SET utf8 DEFAULT NULL COMMENT '任务名称',
                                         `task_explain` varchar(256) CHARACTER SET utf8 DEFAULT NULL COMMENT '任务描述',
                                         `status` varchar(256) CHARACTER SET utf8 DEFAULT '1' COMMENT '状态,1:正常;2:停用',
                                         `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                         `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                         `deleted` int(1) DEFAULT '0' COMMENT '逻辑删除字段',
                                         PRIMARY KEY (`id`),
                                         KEY `logic_id` (`logic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='动态定时任务配置表';

/*Data for the table `spring_scheduled_cron` */

insert  into `spring_scheduled_cron`(`id`,`logic_id`,`cron_key`,`cron_expression`,`task_name`,`task_explain`,`status`,`create_time`,`modify_time`,`deleted`) values
(1,'1111','com.example.dynamictimetask.timeTask.MyTimeTask1','*/10 * * * * ?','任务1','测试定时任务，十秒一次','1','2022-04-08 22:55:28','2022-04-08 22:55:28',0),
(2,'22','com.example.dynamictimetask.timeTask.MyTimeTask2','*/15 * * * * ?','任务2','测试定时任务，十五秒一次','1','2022-04-08 22:55:28','2022-04-08 22:55:28',0),
(5,'555','com.example.dynamictimetask.timeTask.MyTimeTask1','*/5 * * * * ?','任务1-1','测试定时任务，五秒一次','1','2022-04-08 23:20:09','2022-04-08 23:20:09',0),
(6,'666','com.example.dynamictimetask.timeTask.MyTimeTask2','0 0/20 * * * ? ','任务2-2','测试定时任务，二十分钟一次','1','2022-04-09 13:56:09','2022-04-09 13:56:09',0);