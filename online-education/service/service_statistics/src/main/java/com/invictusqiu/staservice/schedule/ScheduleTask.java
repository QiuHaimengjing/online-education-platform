package com.invictusqiu.staservice.schedule;

import com.invictusqiu.staservice.service.StatisticsDailyService;
import com.invictusqiu.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: 定时任务类
 * @className: ScheduleTask.java
 * @author: qqdas
 * @createTime: 2023/10/17 14:16
 */
@Component
public class ScheduleTask {

    @Autowired
    private StatisticsDailyService staService;

    /* 定时器测试
 0/5 * * * * ?表示每隔5秒执行一次这个方法
    @Scheduled(cron = "0/5 * * * * ?")
    public void task1() {
        System.out.println("********************task1执行了...");
    }
*/

    // 在每天凌晨1点，把前一天的数据进行数据查询添加
    @Scheduled(cron = "0 0 1 * * ?")
    public void task2() {
        staService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(),-1)));
    }
}
