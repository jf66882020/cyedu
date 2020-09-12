package com.cy6688.eduservice.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: 俊峰
 * @Date: 2020/9/11 21:28
 */
@Data
public class SubjectData {
    @ExcelProperty(index = 0)
    private String oneLevel;
    @ExcelProperty(index = 1)
    private String twoLevel;
}
