import request from '@/utils/request'

export default {
  // 1.分页查询banner
  getBannerListPage(current, limit) {
    return request({
      url: `/educms/banneradmin/pageBanner/${current}/${limit}`,
      method: 'get'
    })
  },
  // 2.添加轮播图
  addBannerInfo(bannerInfo) {
    return request({
      url: '/educms/banneradmin/addBanner',
      method: 'post',
      data: bannerInfo
    })
  },
  // 3.根据id查询banner
  getBannerInfoId(id) {
    return request({
      url: `/educms/banneradmin/get/${id}`,
      method: 'get'
    })
  },
  // 4.修改轮播图
  updateBannerInfo(bannerInfo) {
    return request({
      url: '/educms/banneradmin/update',
      method: 'put',
      data: bannerInfo
    })
  },
  // 5.删除轮播图
  deleteBannerId(id) {
    return request({
      url: `/educms/banneradmin/remove/${id}`,
      method: 'delete'
    })
  }
}
