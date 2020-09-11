package com.cy6688.servicebase.exceptionhandler;

import com.cy6688.commonutils.R;
import com.cy6688.servicebase.exception.CyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 俊峰
 * @Date: 2020/9/9 17:32
 * 统一异常处理类
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("系统错误，请联系管理员！");
    }

    @ExceptionHandler(CyException.class)
    @ResponseBody
    public R error(CyException exception){
        log.error(exception.getMessage());
        return R.error().code(exception.getCode()).message(exception.getMsg());
    }
}
