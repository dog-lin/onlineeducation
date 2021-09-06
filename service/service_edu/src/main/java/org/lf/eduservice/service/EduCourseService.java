package org.lf.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.lf.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import org.lf.eduservice.entity.vo.CourseInfoForm;
import org.lf.eduservice.entity.vo.CoursePublishVo;
import org.lf.eduservice.entity.vo.CourseQuery;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author linfeng
 * @since 2021-08-25
 */
public interface EduCourseService extends IService<EduCourse> {

    String addCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoFormById(String id);

    void updateCourseInfo(CourseInfoForm courseInfoForm);

    CoursePublishVo getCoursePublishVoById(String id);

    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);

    boolean removeCourseById(String id);
}
