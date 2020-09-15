package com.cy6688.eduservice.service;

import com.cy6688.commonutils.R;
import com.cy6688.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-12
 */
public interface EduVideoService extends IService<EduVideo> {

    //删除小节和视频
    R removeVideoWithFastVideo(String videoId);
}
