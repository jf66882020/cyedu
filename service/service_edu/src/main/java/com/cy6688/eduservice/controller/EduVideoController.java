package com.cy6688.eduservice.controller;


import com.cy6688.commonutils.R;
import com.cy6688.eduservice.entity.EduVideo;
import com.cy6688.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-12
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    //添加小节
    @PostMapping("/add")
    public R add(@RequestBody EduVideo video){
        boolean saveStatus = videoService.save(video);
        if(saveStatus){
            return R.ok();
        }else{
            return R.error();
        }
    }

    //删除小节
    @DeleteMapping("/remove/{videoId}")
    public R remove(@PathVariable("videoId") String videoId){
        boolean removeStatus = videoService.removeById(videoId);
        if(removeStatus){
            return R.ok();
        }else{
            return R.error();
        }
    }

    //编辑小节
    @PostMapping("/edit")
    public R editVideo(@RequestBody EduVideo video){
        boolean updateStatus = videoService.updateById(video);
        if(updateStatus){
            return R.ok();
        }else{
            return R.error();
        }
    }

    //获取小节
    @GetMapping("/get/{videoId}")
    public R getVideo(@PathVariable("videoId") String videoId){
        EduVideo video = videoService.getById(videoId);
        return R.ok().data("item",video);
    }

}


