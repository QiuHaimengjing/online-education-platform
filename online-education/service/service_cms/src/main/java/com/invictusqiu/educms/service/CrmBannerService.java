package com.invictusqiu.educms.service;

import com.invictusqiu.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author qqdas
 * @since 2023-10-11
 */
public interface CrmBannerService extends IService<CrmBanner> {

    // 查询所有banner
    List<CrmBanner> selectAllBanner();
}
