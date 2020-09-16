package com.cy6688.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy6688.commonutils.R;
import com.cy6688.eduservice.entity.CommentInfoVo;
import com.cy6688.eduservice.entity.EduComment;
import com.cy6688.eduservice.service.EduCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-16
 */
@RestController
@RequestMapping("/eduservice/comment")
@CrossOrigin
public class EduCommentController {

    @Autowired
    private EduCommentService commentService;

    @GetMapping("/list/{courseId}/{current}/{limit}")
    public R queryCommentsWithPage(@PathVariable("courseId") String courseId,@PathVariable("current") long current,@PathVariable("limit") long limit){
        Page<EduComment> page = new Page<>(current,limit);
        commentService.page(page,null);
        long total = page.getTotal();
        List<EduComment> records = page.getRecords();
        return R.ok().data("total",total).data("items",records);
    }

    @PostMapping("/add")
    public R addComment(@RequestBody EduComment commentInfo,HttpServletRequest request){
        boolean saveStatus = commentService.addComment(commentInfo, request);
        if(saveStatus){
            return R.ok().message("添加评论成功!");
        }else{
            return R.error().message("添加评论失败!");
        }
    }
}

