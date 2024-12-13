import request from '@/utils/request'

export default {
  // 根据课程id查询评论列表
  getCommentList(page, limit, courseId) {
    return request({
      url: `/eduservice/comment/getCommentInfo/${page}/${limit}`,
      method: 'get',
      params: { courseId }
    })
  },
  // 添加评论
  addComment(comment) {
    return request({
      url: '/eduservice/comment/addComment',
      method: 'post',
      data: comment
    })
  }
}
