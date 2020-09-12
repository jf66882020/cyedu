package com.cy6688.eduservice.entity.chapter;

import lombok.Data;

import java.util.List;

/**
 * @Author: 俊峰
 * @Date: 2020/9/12 15:24
 */
@Data
public class ChapterNode {
    private String id;
    private String title;
    private List<VideoNode> children;
}
