import request from '@/utils/request'

export default {
  // 分页查询讲师的方法
  getTeacherList(current, limit) {
    return request({
      url: `/eduservice/teacherfront/getTeacherFrontList/${current}/${limit}`,
      method: 'get'
    })
  },
  // 讲师详情的方法
  getTeacherInfo(id) {
    return request({
      url: `/eduservice/teacherfront/getTeacherFrontInfo/${id}`,
      method: 'get'
    })
  }
}
