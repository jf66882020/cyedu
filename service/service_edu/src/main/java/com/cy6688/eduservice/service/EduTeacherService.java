package com.cy6688.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy6688.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-08
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String,Object> getFrontTeachers(Page<EduTeacher> pageTeacher);

}
