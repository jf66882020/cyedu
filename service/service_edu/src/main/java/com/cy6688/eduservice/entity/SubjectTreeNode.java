package com.cy6688.eduservice.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author: 俊峰
 * @Date: 2020/9/12 9:43
 */
@Data
public class SubjectTreeNode {
    private String id;
    private String title;
    private List<SubjectTreeNode> children;
}
