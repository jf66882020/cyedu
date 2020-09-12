package com.cy6688.eduservice.service;

import com.cy6688.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy6688.eduservice.entity.SubjectTreeNode;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-11
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
    *@Author: 俊峰
    *@param
    *@return
    *添加课程分类
    */
    void addSubject(MultipartFile file);

    /**
    *@Author: 俊峰
    *@param
    *@return
    *获取课程分类树
    */
    List<SubjectTreeNode> getSubjectTree();

    List<EduSubject> getAllOneSubject();

    List<EduSubject> getSubjectsByParentId(long parent_id);
}
