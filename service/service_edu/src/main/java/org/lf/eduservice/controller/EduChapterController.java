package org.lf.eduservice.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.lf.commonutils.R;
import org.lf.eduservice.entity.EduChapter;
import org.lf.eduservice.entity.vo.ChapterVo;
import org.lf.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author linfeng
 * @since 2021-08-31
 */
@Api(description = "章节管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/educhapter")

public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;

    @ApiOperation(value = "根据id查询章节")
    @GetMapping("getChapterVideoById/{courseId}")
    public R getChapterVideoById(@PathVariable String courseId){
        List<ChapterVo> chapterVoList = chapterService.getChapterVideoById(courseId);
        return R.ok().data("chapterVoList", chapterVoList);
    }

    @ApiOperation(value = "新增章节")
    @PostMapping("addChapter")
    public R addChapter(
            @ApiParam(name = "chapterVo", value = "章节对象", required = true)
            @RequestBody EduChapter chapter){

        chapterService.save(chapter);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除章节")
    @DeleteMapping("delChapterById/{id}")
    public R delChapterById(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String id){

        boolean result = chapterService.removeById(id);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }

    @ApiOperation(value = "根据ID查询章节")
    @GetMapping("getChapterById/{id}")
    public R getChapterById(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String id){

        EduChapter chapter = chapterService.getById(id);
        return R.ok().data("chapter", chapter);
    }

    @ApiOperation(value = "根据ID修改章节")
    @PostMapping("updateChapterById")
    public R updateChapterById(
            @ApiParam(name = "chapter", value = "章节对象", required = true)
            @RequestBody EduChapter chapter){

        chapterService.updateById(chapter);
        return R.ok();
    }
}

