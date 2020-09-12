package com.cy6688.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy6688.eduservice.entity.EduSubject;
import com.cy6688.eduservice.entity.SubjectData;
import com.cy6688.eduservice.service.EduSubjectService;
import com.cy6688.servicebase.exception.CyException;
import lombok.NoArgsConstructor;

/**
 * @Author: 俊峰
 * @Date: 2020/9/11 21:33
 */
@NoArgsConstructor
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    private EduSubjectService subjectService;

    public SubjectExcelListener(EduSubjectService subjectService){
        this.subjectService = subjectService;
    }

   /**
   *@Author: 俊峰
   *@param
   *@return
   *逐行读取excel的数据
   */
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null){
            throw new CyException(20001,"文件数据为空");
        }
        String oneLevel = subjectData.getOneLevel();
        String twoLevel = subjectData.getTwoLevel();

        EduSubject eduSubject = existSubject(oneLevel, "0");
        //判断一级分类是否存在

        if(eduSubject == null){
            //如果不存在，执行此块
            eduSubject = new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setTitle(oneLevel);
            subjectService.save(eduSubject);
        }

        EduSubject twoSubject = existSubject(twoLevel, eduSubject.getId());
        if(twoSubject == null){
            twoSubject = new EduSubject();
            twoSubject.setParentId(eduSubject.getId());
            twoSubject.setTitle(twoLevel);
            subjectService.save(twoSubject);
        }

    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *判断一级二级分类是否存在
    */
    private EduSubject existSubject(String name,String subjectId){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",subjectId);
        EduSubject one = subjectService.getOne(wrapper);
        return one;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
