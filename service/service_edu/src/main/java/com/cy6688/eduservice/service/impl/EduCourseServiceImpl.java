package com.cy6688.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy6688.eduservice.entity.CourseInfoVO;
import com.cy6688.eduservice.entity.EduCourse;
import com.cy6688.eduservice.entity.EduCourseDescription;
import com.cy6688.eduservice.mapper.EduCourseMapper;
import com.cy6688.eduservice.service.EduCourseDescriptionService;
import com.cy6688.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy6688.servicebase.exception.CyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

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
}
