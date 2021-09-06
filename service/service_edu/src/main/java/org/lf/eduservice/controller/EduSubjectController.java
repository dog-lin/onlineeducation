package org.lf.eduservice.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.lf.commonutils.R;
import org.lf.eduservice.entity.vo.OneSubjectVo;
import org.lf.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author linfeng
 * @since 2021-08-24
 */
@Api(description = "课程管理")
@RestController
@RequestMapping("/eduservice/edusubject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    @ApiOperation(value = "导入课程")
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        subjectService.addSubject(file,subjectService);
        return R.ok();
    }


    @ApiOperation(value = "查询所有课程分类")
    @GetMapping("getAllSubject")
    public R getAllSubject(){

        List<OneSubjectVo> subjectVoList = subjectService.getAllSubject();
        return R.ok().data("allSubject", subjectVoList);
    }
}

