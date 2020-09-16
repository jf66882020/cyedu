package com.cy6688.ucenterservice.controller;


import com.cy6688.commonutils.JwtUtils;
import com.cy6688.commonutils.R;
import com.cy6688.ucenterservice.entity.UcenterMember;
import com.cy6688.ucenterservice.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-15
 */
@RestController
@RequestMapping("/ucenterservice/member/")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    @PostMapping("login")
    public R login(@RequestBody UcenterMember member){
        String token = memberService.login(member);
        return R.ok().data("item",token);
    }

    @PostMapping("register")
    public R register(@RequestBody UcenterMember member){
        boolean registerStatus = memberService.register(member);
        if(registerStatus){
            return R.ok().message("注册成功！");
        }else{
            return R.error().message("注册失败！");
        }
    }

    @GetMapping("info")
    public R getUserInfo(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = memberService.getUserInfo(memberId);
        return R.ok().data("item",member);
    }

    @GetMapping("front/info/{memberId}")
    public UcenterMember getUser(@PathVariable("memberId") String memberId){
        UcenterMember member = memberService.getUserInfo(memberId);
        return member;
    }



}

