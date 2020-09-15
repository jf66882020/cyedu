package com.cy6688.ucenterservice.service;

import com.cy6688.ucenterservice.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-15
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    boolean register(UcenterMember member);

    UcenterMember getUserInfo(String memberId);
}
