package com.invictusqiu.staservice.controller;


import com.invictusqiu.commonutils.Result;
import com.invictusqiu.staservice.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author qqdas
 * @since 2023-10-17
 */
@RestController
@RequestMapping("/staservice/sta")
//@CrossOrigin
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService staService;

    // 统计某一天注册人数，生成统计数据
    @PostMapping("registerCount/{day}")
    public Result registerCount(@PathVariable String day) {
        staService.registerCount(day);
        return Result.ok();
    }

    // 图表显示，返回两部分数据，日期json数组，数量json数组
    @GetMapping("showData/{type}/{begin}/{end}")
    public Result showData(@PathVariable String type,@PathVariable String begin,
                           @PathVariable String end) {
        Map<String,Object> map = staService.getShowData(type,begin,end);
        return Result.ok().data(map);
    }

}

