package com.cy6688.eduservice.entity;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @Author: 俊峰
 * @Date: 2020/9/9 16:40
 */
@Data
public class TeacherQuery {
    @ApiModelProperty(value = "讲师姓名")
    private String name;

    @ApiModelProperty(value = "讲师级别")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间",example = "2020-09-09 00:00:00")
    private String begin;

    @ApiModelProperty(value = "查询结束时间",example = "2020-09-09 23:00:00")
    private String end;

}
