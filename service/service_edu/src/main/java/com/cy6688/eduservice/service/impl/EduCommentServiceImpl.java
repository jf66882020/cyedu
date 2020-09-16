package com.cy6688.eduservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.cy6688.commonutils.JwtUtils;
import com.cy6688.commonutils.R;
import com.cy6688.eduservice.client.UcenterClient;
import com.cy6688.eduservice.entity.EduComment;
import com.cy6688.eduservice.entity.UcenterMember;
import com.cy6688.eduservice.mapper.EduCommentMapper;
import com.cy6688.eduservice.service.EduCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy6688.servicebase.exception.CyException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-16
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public boolean addComment(EduComment commentInfo, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberId)) {
            throw new CyException(20001,"请登录！");
        }
        commentInfo.setMemberId(memberId);
        UcenterMember user = ucenterClient.getUser(memberId);
        if(user == null){
            throw new CyException(20001,"获取用户信息失败!");
        }
        commentInfo.setNickname(user.getNickname());
        commentInfo.setAvatar(user.getAvatar());
        boolean saveStatus = this.save(commentInfo);
        return saveStatus;
    }
}
