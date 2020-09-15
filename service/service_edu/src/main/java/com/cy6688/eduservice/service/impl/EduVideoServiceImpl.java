package com.cy6688.eduservice.service.impl;

import com.cy6688.commonutils.R;
import com.cy6688.eduservice.client.VodClient;
import com.cy6688.eduservice.entity.EduVideo;
import com.cy6688.eduservice.mapper.EduVideoMapper;
import com.cy6688.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-12
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    /**
    *@Author: 俊峰
    *@param
    *@return
    *删除小节和视频
    */
    @Override
    @Transactional
    public R removeVideoWithFastVideo(String videoId) {
        //1.删除fastdfs上的视频
        EduVideo eduVideo = baseMapper.selectById(videoId);
        String videoSourceId = eduVideo.getVideoSourceId();
        baseMapper.deleteById(videoId);

        R r = vodClient.removeVideo(videoSourceId);

        //2.删除数据库中的小节记录

        return r;
    }
}
