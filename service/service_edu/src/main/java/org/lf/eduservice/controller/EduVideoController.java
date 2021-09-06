package org.lf.eduservice.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.lf.commonutils.R;
import org.lf.eduservice.client.VodClient;
import org.lf.eduservice.entity.EduVideo;
import org.lf.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author linfeng
 * @since 2021-08-31
 */
@RestController
@RequestMapping("/eduservice/eduvideo")
@CrossOrigin
@Api(description = "小节管理")
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;

    @Autowired
    private VodClient vodClient;

//    @ApiOperation(value = "根据id查询章节")
//    @GetMapping("getChapterVideoById/{courseId}")
//    public R getChapterVideoById(@PathVariable String courseId){
//        List<VideoVo> videoVoList = videoService.getVideoById(courseId);
//        return R.ok().data("videoVoList", videoVoList);
//    }

    @ApiOperation(value = "新增小节")
    @PostMapping("addVideo")
    public R addVideo(
            @ApiParam(name = "video", value = "章节对象", required = true)
            @RequestBody EduVideo video){

        videoService.save(video);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除小节")
    @DeleteMapping("delVideoById/{id}")
    public R delVideoById(
            @ApiParam(name = "id", value = "小节ID", required = true)
            @PathVariable String id){

        EduVideo eduVideo = videoService.getById(id);
        String videoId = eduVideo.getVideoSourceId();
        if(!"".equals(videoId)){
            vodClient.removeVideo(videoId);
        }

        boolean result = videoService.removeById(id);

        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }

    @ApiOperation(value = "根据ID查询小节")
    @GetMapping("getVideoById/{id}")
    public R getVideoById(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String id){

        EduVideo video = videoService.getById(id);
        return R.ok().data("video", video);
    }

    @ApiOperation(value = "根据ID修改小节")
    @PostMapping("updateVideoById")
    public R updateVideoById(
            @ApiParam(name = "video", value = "小节对象", required = true)
            @RequestBody EduVideo video){

        videoService.updateById(video);
        return R.ok();
    }
}

