package com.invictusqiu.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.invictusqiu.commonutils.Result;
import com.invictusqiu.educms.entity.CrmBanner;
import com.invictusqiu.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * 后台使用
 * </p>
 *
 * @author qqdas
 * @since 2023-10-11
 */
@RestController
@RequestMapping("/educms/banneradmin")
//@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;

    // 1.分页查询banner
    @GetMapping("pageBanner/{current}/{limit}")
    public Result pageBanner(@PathVariable long current, @PathVariable long limit) {
        Page<CrmBanner> pageBanner = new Page<>(current,limit);
        bannerService.page(pageBanner,null);
        return Result.ok().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }

    // 2.添加banner
    @PostMapping("addBanner")
    public Result addBanner(@RequestBody CrmBanner crmBanner) {
        bannerService.save(crmBanner);
        return Result.ok();
    }

    // 3.根据id查询banner(回显)
    @GetMapping("get/{id}")
    public Result getBannerById(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return Result.ok().data("item",banner);
    }

    // 4.修改banner
    @PutMapping("update")
    public Result updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return Result.ok();
    }

    // 5.删除banner
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        bannerService.removeById(id);
        return Result.ok();
    }

}

