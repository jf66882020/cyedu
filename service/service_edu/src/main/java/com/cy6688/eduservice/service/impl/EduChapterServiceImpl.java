package com.cy6688.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy6688.eduservice.entity.EduChapter;
import com.cy6688.eduservice.entity.EduVideo;
import com.cy6688.eduservice.entity.chapter.ChapterNode;
import com.cy6688.eduservice.entity.chapter.VideoNode;
import com.cy6688.eduservice.mapper.EduChapterMapper;
import com.cy6688.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy6688.eduservice.service.EduVideoService;
import com.cy6688.servicebase.exception.CyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-12
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;


    /**
     *@Author: 俊峰
     *@param
     *@return
     *根据课程Id获取章节和小节
     */
    @Override
    public List<ChapterNode> getChapterVideoListByCourseId(String courseId) {
        //1.获取对应课程章节
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<EduChapter> chapters = baseMapper.selectList(wrapper);

        List<ChapterNode> result = new ArrayList<>();

        //2.获取所有属于该课程的小节
        QueryWrapper wrapper1 = new QueryWrapper();
        wrapper1.eq("course_id",courseId);
        List<EduVideo> vidoes = videoService.list(wrapper1);

        chapters.forEach(chapter -> {
            ChapterNode chapterNode = new ChapterNode();
            chapterNode.setId(chapter.getId());
            chapterNode.setTitle(chapter.getTitle());
            chapterNode.setChildren(getVideosByChapterId(chapter.getId(),vidoes));
            result.add(chapterNode);
        });
        return result;
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *根据章节id，删除章节信息
    */
    @Override
    @Transactional
    public boolean removeChapterById(String chapterId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("id",chapterId);
        int deleteStatus = baseMapper.delete(wrapper);
        if(deleteStatus <= 0){
            throw new CyException(20001,"删除章节失败！");
        }

        QueryWrapper<EduVideo> wrapper1 = new QueryWrapper();
        wrapper1.eq("chapter_id",chapterId);
        boolean removeStatus = videoService.remove(wrapper1);
        if(!removeStatus){
            throw new CyException(20001,"删除小节失败！");
        }
        return deleteStatus > 0 && removeStatus;
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *根据章节id，获取小节列表
    */
    private List<VideoNode> getVideosByChapterId(String id,List<EduVideo> videos) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        List<EduVideo> collect = videos.stream().filter(video -> video.getChapterId().equals(id)).collect(Collectors.toList());
        List<VideoNode> result = new ArrayList<>();
        collect.forEach(video -> {
            VideoNode node = new VideoNode();
            node.setId(video.getId());
            node.setTitle(video.getTitle());
            node.setIsFree(video.getIsFree());
            node.setVideoSourceId(video.getVideoSourceId());
            result.add(node);
        });
        return result;
    }
}
