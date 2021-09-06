package org.lf.eduservice.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.lf.commonutils.R;
import org.lf.eduservice.entity.EduCourse;
import org.lf.eduservice.entity.EduTeacher;
import org.lf.eduservice.service.EduCourseService;
import org.lf.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservice/index")
@CrossOrigin
public class IndexController {

    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;

    //查询前8条热门课程，查询前4条名师
    @GetMapping("getCourseTeacherList")
    public R getCourseTeacherList() {
        //查询前8条热门课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        wrapper.last("LIMIT 8");
        List<EduCourse> eduList = courseService.list(wrapper);

        //查询前4条名师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("gmt_create");
        wrapperTeacher.last("LIMIT 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);

        return R.ok().data("courseList",eduList).data("teacherList",teacherList);
    }
}
