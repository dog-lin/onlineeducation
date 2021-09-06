package org.lf.eduservice.service.impl;

import org.lf.eduservice.entity.EduVideo;
import org.lf.eduservice.mapper.EduVideoMapper;
import org.lf.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author linfeng
 * @since 2021-08-31
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

}
