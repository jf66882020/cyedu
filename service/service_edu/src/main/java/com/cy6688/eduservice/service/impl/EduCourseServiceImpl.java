package com.cy6688.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy6688.eduservice.entity.*;
import com.cy6688.eduservice.entity.course.CourseQuery;
import com.cy6688.eduservice.entity.course.PublishCourseInfo;
import com.cy6688.eduservice.mapper.EduCourseMapper;
import com.cy6688.eduservice.service.EduChapterService;
import com.cy6688.eduservice.service.EduCourseDescriptionService;
import com.cy6688.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy6688.eduservice.service.EduVideoService;
import com.cy6688.servicebase.exception.CyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-12
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private EduVideoService videoService;

    /**
    *@Author: 俊峰
    *@param
    *@return
    *添加课程基本信息
    */
    @Override
    public String saveCourseInfo(CourseInfoVO courseInfoVO) {
        //保存课程信息
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoVO,course);

        int insertStatus = baseMapper.insert(course);
        if(insertStatus <= 0){
            throw new CyException(20001,"添加课程信息失败");
        }

        //保存课程描述信息
        String courseId = course.getId();
        EduCourseDescription description = new EduCourseDescription();
        description.setDescription(courseInfoVO.getDescription());
        description.setId(courseId);
        courseDescriptionService.save(description);
        return courseId;
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *根据课程id获取课程基本信息
    */
    @Override
    public CourseInfoVO getCourseInfo(String courseId) {
        CourseInfoVO result = new CourseInfoVO();

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("id",courseId);
        EduCourse course = baseMapper.selectOne(wrapper);
        BeanUtils.copyProperties(course,result);

        EduCourseDescription description = courseDescriptionService.getById(courseId);
        result.setDescription(description.getDescription());
        return result;
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *修改课程基本信息
    */
    @Override
    public void editCourseInfo(CourseInfoVO courseInfoVO) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVO,eduCourse);
        int updateStatus = baseMapper.updateById(eduCourse);

        if(updateStatus <= 0){
            throw new CyException(20001,"更新课程失败！");
        }

        EduCourseDescription description = new EduCourseDescription();
        description.setId(eduCourse.getId());
        description.setDescription(courseInfoVO.getDescription());
        boolean updateStatus2 = courseDescriptionService.updateById(description);
        if(!updateStatus2){
            throw new CyException(20001,"更新课程描述信息失败！");
        }
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *获取发布课程信息
    */
    @Override
    public PublishCourseInfo getCoursePublishInfo(String courseId) {
        PublishCourseInfo publishCourseInfo = baseMapper.getPublishCourseInfo(courseId);
        return publishCourseInfo;
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *分页查询课程信息
    */
    @Override
    public List<PublishCourseInfo> getCourseListWithPage(CourseQuery courseQuery, long current, long limit) {
        HashMap map = new HashMap<>();
        map.put("courseQuery",courseQuery);

        current = (current - 1) * limit;
        map.put("current",current);
        map.put("limit",limit);
        List<PublishCourseInfo> listWithCoditionPage = baseMapper.getListWithCoditionPage(map);
        return listWithCoditionPage;
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *根据条件获取总条数
    */
    @Override
    public long getCourseListWithPageSize(CourseQuery courseQuery) {
        HashMap map = new HashMap<>();
        map.put("courseQuery",courseQuery);
        long size = baseMapper.getListWithCoditionPageSize(map);
        return size;
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *根据课程id删除课程信息
     * //TODO 将来要做删除视频部分
    */
    @Override
    @Transactional
    public boolean removeCourseById(String courseId) {
        //1.删除课程信息
        boolean courseDeleteStatus = this.removeById(courseId);

        //2.删除章节信息
        QueryWrapper<EduChapter> chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("course_id",courseId);
        boolean chapterDeleteStatus = chapterService.remove(chapterWrapper);

        //3.删除小节信息
        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id",courseId);
        boolean videoDeleteStatus = videoService.remove(videoWrapper);

        //4.删除课程描述信息----由于课程信息和描述信息是1v1的关系，所以可以根据课程id直接删除描述信息
        boolean descriptionDeleteStatus = courseDescriptionService.removeById(courseId);

        boolean transactionDeleteStatus = courseDeleteStatus && chapterDeleteStatus && videoDeleteStatus && descriptionDeleteStatus;
        if(!transactionDeleteStatus){
            throw new CyException(20001,"删除课程失败！");
        }

        return transactionDeleteStatus;
    }
}
