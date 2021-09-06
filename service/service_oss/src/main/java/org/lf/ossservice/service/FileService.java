package org.lf.ossservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadFileOss(MultipartFile file);
}
