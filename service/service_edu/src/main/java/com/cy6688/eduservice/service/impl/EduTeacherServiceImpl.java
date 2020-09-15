package com.cy6688.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy6688.eduservice.entity.EduTeacher;
import com.cy6688.eduservice.mapper.EduTeacherMapper;
import com.cy6688.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-08
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public Map<String,Object> getFrontTeachers(Page<EduTeacher> pageTeacher) {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper();
        wrapper.orderByAsc("sort");
        baseMapper.selectPage(pageTeacher,wrapper);

        List<EduTeacher> teachers = pageTeacher.getRecords();
        long total = pageTeacher.getTotal();
        long size = pageTeacher.getSize();
        long current = pageTeacher.getCurrent();
        long pages = pageTeacher.getPages();
        boolean hasNext = pageTeacher.hasNext();
        boolean hasPrevious = pageTeacher.hasPrevious();

        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("size",size);
        map.put("current",current);
        map.put("pages",pages);
        map.put("hasNext",hasNext);
        map.put("hasPrevious",hasPrevious);
        map.put("items",teachers);

        return map;
    }
}
