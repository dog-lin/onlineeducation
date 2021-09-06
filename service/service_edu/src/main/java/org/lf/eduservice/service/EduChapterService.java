package org.lf.eduservice.service;

import org.lf.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import org.lf.eduservice.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author linfeng
 * @since 2021-08-31
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoById(String courseId);
}
