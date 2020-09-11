package com.cy6688.servicebase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 俊峰
 * @Date: 2020/9/9 17:56
 * 自定义异常类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CyException extends RuntimeException{
    //状态码
    private Integer code;

    //错误信息
    private String msg;

}
