package com.hy.spring.cloud.util.service;

import com.hy.spring.cloud.util.domain.Entity.Attachment;
import com.hy.spring.cloud.util.domain.Entity.Message;

public interface UtilService {

    Message uploadAttachment(Attachment attachment);

}
