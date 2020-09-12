package com.cy6688.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy6688.eduservice.entity.EduSubject;
import com.cy6688.eduservice.entity.SubjectData;
import com.cy6688.eduservice.entity.SubjectTreeNode;
import com.cy6688.eduservice.listener.SubjectExcelListener;
import com.cy6688.eduservice.mapper.EduSubjectMapper;
import com.cy6688.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-11
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    /**
     * 读取上传的excel文件，新建课程分类
     * @param file
     */
    @Override
    public void addSubject(MultipartFile file) {
        try{
            EasyExcel.read(file.getInputStream(), SubjectData.class,new SubjectExcelListener(this)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *获取课程分类树
    */
    @Override
    public List<SubjectTreeNode> getSubjectTree() {
        List<SubjectTreeNode> result = new ArrayList<>();

        List<EduSubject> eduSubjects = baseMapper.selectList(null);
        //过滤出所有一级分类
        List<EduSubject> oneLevel = eduSubjects.stream().filter(subject -> "0".equalsIgnoreCase(subject.getParentId())).collect(Collectors.toList());
        //封装树
        oneLevel.forEach(oneSubject -> {
            List<SubjectTreeNode> tempTwoSubjects = new ArrayList<>();
            List<EduSubject> twoLevelCollect = eduSubjects.stream().filter(subject -> subject.getParentId().equalsIgnoreCase(oneSubject.getId())).collect(Collectors.toList());
            SubjectTreeNode subjectTreeNode = new SubjectTreeNode();
            subjectTreeNode.setId(oneSubject.getId());
            subjectTreeNode.setTitle(oneSubject.getTitle());
            subjectTreeNode.setChildren(this.getTwoLevelTree(oneSubject.getId(),eduSubjects));
            result.add(subjectTreeNode);
        });
        return result;
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *获取所有一级分类
    */
    @Override
    public List<EduSubject> getAllOneSubject() {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",0);
        List<EduSubject> eduSubjects = baseMapper.selectList(wrapper);
        return eduSubjects;
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *根据一级分类id，获取所有二级分类
    */
    @Override
    public List<EduSubject> getSubjectsByParentId(long parent_id) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",parent_id);
        List<EduSubject> eduSubjects = baseMapper.selectList(wrapper);
        return eduSubjects;
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *根据一级分类查询二级分类树
    */
    private List<SubjectTreeNode> getTwoLevelTree(String parentId,List<EduSubject> eduSubjects){
        List<SubjectTreeNode> result = new ArrayList<>();
        List<EduSubject> collect = eduSubjects.stream().filter(subject -> subject.getParentId().equalsIgnoreCase(parentId)).collect(Collectors.toList());
        collect.forEach(subject -> {
            SubjectTreeNode subjectTreeNode = new SubjectTreeNode();
            subjectTreeNode.setId(subject.getId());
            subjectTreeNode.setTitle(subject.getTitle());
            result.add(subjectTreeNode);
        });
        return result;
    }
}
