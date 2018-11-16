package com.hy.spring.cloud.util.service;

import com.hy.spring.cloud.util.domain.Entity.Attachment;
import com.hy.spring.cloud.util.domain.Entity.Message;
import org.springframework.web.multipart.MultipartFile;

public interface UtilService {

    Message uploadAttachment(MultipartFile file);

}
