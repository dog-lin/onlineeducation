package org.lf.eduservice.service;

import org.lf.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.lf.eduservice.entity.vo.OneSubjectVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author linfeng
 * @since 2021-08-24
 */
public interface EduSubjectService extends IService<EduSubject> {

    void addSubject(MultipartFile file, EduSubjectService subjectService);

    List<OneSubjectVo> getAllSubject();
}
