package com.invictusqiu.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.invictusqiu.commonutils.JwtUtils;
import com.invictusqiu.commonutils.Result;
import com.invictusqiu.commonutils.ordervo.UcenterMemberOrder;
import com.invictusqiu.eduservice.client.UcenterClient;
import com.invictusqiu.eduservice.entity.EduComment;
import com.invictusqiu.eduservice.service.EduCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author qqdas
 * @since 2023-10-31
 */
@RestController
@RequestMapping("/eduservice/comment")
public class EduCommentController {

    @Autowired
    private EduCommentService commentService;

    @Autowired
    private UcenterClient ucenterClient;

    // 根据课程id分页查询评论列表
    @GetMapping("getCommentInfo/{page}/{limit}")
    public Result getCommentInfo(@PathVariable long page, @PathVariable long limit, String courseId) {
        Page<EduComment> commentPage = new Page<>(page,limit);
        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        wrapper.eq("course_id",courseId);

        IPage<EduComment> iPage = commentService.page(commentPage, wrapper);
        List<EduComment> records = iPage.getRecords();  // 当前页的记录
        long total = iPage.getTotal();  // 总记录数字

        Map map = new HashMap();
        map.put("items",records);
        map.put("total",total);
        return Result.ok().data(map);
    }

    // 添加评论
    @PostMapping("addComment")
    public Result addComment(@RequestBody EduComment comment, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(memberId)) {
            return Result.error().code(28004).message("请登录");
        }
        // 如果不为空证明已登录
        comment.setMemberId(memberId);
        // 远程调用ucenter根据用户id获取用户信息
        UcenterMemberOrder ucenterInfo = ucenterClient.getUserInfoOrder(memberId);

        comment.setNickname(ucenterInfo.getNickname());
        comment.setAvatar(ucenterInfo.getAvatar());

        commentService.save(comment);
        return Result.ok();
    }
}

