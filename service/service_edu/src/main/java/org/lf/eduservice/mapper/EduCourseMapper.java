package org.lf.eduservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lf.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.lf.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author linfeng
 * @since 2021-08-25
 */

public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo getCoursePublishVoById(String id);
}
