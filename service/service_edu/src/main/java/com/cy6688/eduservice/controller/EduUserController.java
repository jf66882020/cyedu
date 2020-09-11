package com.cy6688.eduservice.controller;

import com.cy6688.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 俊峰
 * @Date: 2020/9/10 10:23
 * 用户管理接口
 */
@RestController
@Slf4j
@RequestMapping("/eduservice/user/")
@CrossOrigin
public class EduUserController {

    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://pic2.zhimg.com/80/v2-867a95c44703177811f2590b09396113_720w.jpg?source=1940ef5c");
    }
}
