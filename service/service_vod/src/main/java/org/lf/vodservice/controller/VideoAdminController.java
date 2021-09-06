package org.lf.vodservice.controller;

;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.lf.baseservice.handler.MyException;
import org.lf.commonutils.R;
import org.lf.vodservice.utils.AliyunVodSDKUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Api(description="阿里云视频点播微服务")
@CrossOrigin //跨域
@RestController
@RequestMapping("/eduvod/video")
@Slf4j
public class VideoAdminController {

//    @Autowired
//    private VideoService videoService;

    @PostMapping("upload")
    public R uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) throws Exception {

        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));

            UploadStreamRequest request = new UploadStreamRequest(
                    "LTAI5tBcRfQedFg9zUjAXGdX",
                    "5EelM43TRlTg47XeNaWTtv74VQcNF7",
                    title, originalFilename, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = response.getVideoId();
            log.info("=====================" + videoId + "===========================");
            return R.ok().data("videoId",videoId);

        }catch (IOException e){
            e.printStackTrace();
            throw new MyException(20001,"上传视频失败");
        }
    }

    @DeleteMapping("/delVideo/{videoId}")
    public R removeVideo(@ApiParam(name = "videoId", value = "云端视频id", required = true)
                         @PathVariable("videoId") String videoId){

        try{
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    "LTAI5tBcRfQedFg9zUjAXGdX",
                    "5EelM43TRlTg47XeNaWTtv74VQcNF7");

            DeleteVideoRequest request = new DeleteVideoRequest();


            request.setVideoIds(videoId);
            //DeleteVideoResponse response = client.getAcsResponse(request);

            //System.out.print("RequestId = " + response.getRequestId() + "\n");
            client.getAcsResponse(request);
        }catch (ClientException | com.netflix.client.ClientException e){
            throw new MyException(20001, "视频删除失败");
        }
        return R.ok();
    }

    @DeleteMapping("/delVideoList")
    public R removeVideoList(@RequestParam("videoList") List<String> videoList) {
        try{
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    "LTAI5tBcRfQedFg9zUjAXGdX",
                    "5EelM43TRlTg47XeNaWTtv74VQcNF7");

            DeleteVideoRequest request = new DeleteVideoRequest();

            String videos = StringUtils.join(videoList,",");
            request.setVideoIds(videos);
            //DeleteVideoResponse response = client.getAcsResponse(request);

            //System.out.print("RequestId = " + response.getRequestId() + "\n");
            client.getAcsResponse(request);
        }catch (ClientException | com.netflix.client.ClientException e){
            throw new MyException(20001, "批量视频删除失败");
        }
        return R.ok();
    }
}