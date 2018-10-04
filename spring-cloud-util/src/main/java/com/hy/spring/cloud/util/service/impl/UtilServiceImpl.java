package com.hy.spring.cloud.util.service.impl;

import com.hy.spring.cloud.util.domain.Entity.Attachment;
import com.hy.spring.cloud.util.domain.Entity.Message;
import com.hy.spring.cloud.util.mapper.UtilMapper;
import com.hy.spring.cloud.util.service.UtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FileName: UtilServiceImpl
 * Author:   hy103
 * Date:     2018/8/3 23:34
 * Description: Util ServiceImpl
 */

@Service
public class UtilServiceImpl implements UtilService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UtilMapper utilMapper;

    public Message uploadAttachment(Attachment attachment){
        utilMapper.uploadAttachment(attachment);
        return Message.info("ok");
    }
}
