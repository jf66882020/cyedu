package com.cy6688.ucenterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy6688.commonutils.JwtUtils;
import com.cy6688.commonutils.MD5;
import com.cy6688.servicebase.exception.CyException;
import com.cy6688.ucenterservice.entity.UcenterMember;
import com.cy6688.ucenterservice.mapper.UcenterMemberMapper;
import com.cy6688.ucenterservice.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-15
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    /**
    *@Author: 俊峰
    *@param
    *@return
    *前端登录
    */
    @Override
    public String login(UcenterMember member) {
        String mobile = member.getMobile();
        String password = member.getPassword();

        //1.判断用户名或者密码是否为空
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new CyException(20001,"用户名或者密码不能为空!");
        }

        //2.判断用户是否存在
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember ucenterMember = baseMapper.selectOne(wrapper);
        if(ucenterMember == null){
            throw new CyException(20001,"用户不存在!");
        }

        //3.判断密码是否错误
        if(!MD5.encrypt(password).equalsIgnoreCase(ucenterMember.getPassword())){
            throw new CyException(20001,"密码错误!");
        }

        //4.判断用户是否被禁用
        if(ucenterMember.getIsDisabled() == 1){
            throw new CyException(20001,"用户被禁用!");
        }

        //5.返回token
        String jwtToken = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
        return jwtToken;
    }

    @Override
    public boolean register(UcenterMember member) {
        String mobile = member.getMobile();
        String password = member.getPassword();
        String nickname = member.getNickname();

        //1.判断用户名和密码是否为空
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new CyException(20001,"用户名或者密码不允许为空!");
        }

        //2.判断用户名是否已经注册过
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        int count = this.count(wrapper);
        if(count > 0){
            throw new CyException(20001,"用户名已经存在!");
        }

        //3.数据存储到数据库中
        boolean saveStatus = this.save(member);
        if(saveStatus){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public UcenterMember getUserInfo(String memberId) {
        UcenterMember member = this.getById(memberId);
        member.setPassword("");
        return member;
    }
}
