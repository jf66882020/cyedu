package com.cy6688.eduservice.service;

import com.cy6688.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy6688.eduservice.entity.chapter.ChapterNode;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-12
 */
public interface EduChapterService extends IService<EduChapter> {

    //根据课程Id获取章节和小节
    List<ChapterNode> getChapterVideoListByCourseId(String courseId);

    boolean removeChapterById(String chapterId);
}
