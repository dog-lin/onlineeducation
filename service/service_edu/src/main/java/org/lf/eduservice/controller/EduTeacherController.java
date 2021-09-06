package org.lf.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.lf.commonutils.R;
import org.lf.eduservice.entity.EduTeacher;
import org.lf.eduservice.entity.vo.TeacherQuery;
import org.lf.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author linfeng
 * @since 2021-08-18
 */
@Api(description = "教师管理")
@RestController
@RequestMapping("/eduservice/eduteacher")
@Slf4j
@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "查询所有教师")
    @GetMapping
    public R getAllTeacher() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("list",list);
    }

    @ApiOperation(value = "根据id删除教师")
    @DeleteMapping("{id}")
    public R delTeacher(@PathVariable String id) {
        return eduTeacherService.removeById(id) ? R.ok() : R.error();
    }


    @ApiOperation(value = "分页查询教师")
    @GetMapping("getTeacherPage/{page}/{limit}")
    public R getTeacherPage(
            @ApiParam(name = "page", value = "当前页数", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        Page<EduTeacher> pageParam = new Page<>(page, limit);

        eduTeacherService.page(pageParam, null);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return  R.ok().data("total", total).data("rows", records);
    }


    @ApiOperation(value = "带条件分页查询教师")
    @PostMapping("getTeacherPageVo/{page}/{limit}")
    public R getTeacherPageVo(
            @ApiParam(name = "page", value = "当前页数", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @RequestBody TeacherQuery teacherQuery){//@RequestBody把json串转化为实体类


        log.info("==========================="+teacherQuery.toString()+"========================");
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();

        if(!StringUtils.isEmpty(name)) {
            queryWrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            queryWrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create",end);
        }


        Page<EduTeacher> pageParam = new Page<>(page, limit);

        eduTeacherService.page(pageParam, queryWrapper);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return  R.ok().data("total", total).data("rows", records);
    }


    @ApiOperation(value = "新增教师")
    @PostMapping("addTeacher")
    public R addTeacher(
            @ApiParam(name = "teacher", value = "教师对象", required = true)
            @RequestBody EduTeacher teacher){

        return eduTeacherService.save(teacher) ? R.ok() : R.error();
    }


    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("/getTeacherById/{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("teacher", teacher);
    }


    @ApiOperation(value = "修改讲师")
    @PostMapping("updateTeacher")
    public R updateById(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){

        return eduTeacherService.updateById(teacher) ? R.ok() : R.error();
    }
}

