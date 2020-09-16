package com.cy6688.eduservice.service;

import com.cy6688.eduservice.entity.EduComment;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-16
 */
public interface EduCommentService extends IService<EduComment> {

    boolean addComment(EduComment commentInfo, HttpServletRequest request);
}
