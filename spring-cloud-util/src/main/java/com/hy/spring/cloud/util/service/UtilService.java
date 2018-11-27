package com.hy.spring.cloud.util.service;

import com.hy.spring.cloud.util.domain.Entity.Attachment;
import com.hy.spring.cloud.util.domain.Entity.Message;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UtilService {

    Message uploadAttachment(MultipartFile file);

    Message saveFileList(List<Attachment> attachmenList);
}
