package com.hy.spring.cloud.util.service.impl;

import com.hy.spring.cloud.util.domain.Entity.Attachment;
import com.hy.spring.cloud.util.domain.Entity.Message;
import com.hy.spring.cloud.util.mapper.ImageMapper;
import com.hy.spring.cloud.util.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * FileName: ImageServiceImpl
 * Author:   hy103
 * Date:     2018/11/28 22:19
 * Description: ImageServiceImpl
 */
@Service
public class ImageServiceImpl implements ImageService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ImageMapper imageMapper;

    public String getImage(String fileId){
        Attachment attachment = imageMapper.getImage(fileId);
        return attachment.getPathUrl();
    }

    public List<Attachment> getAllImage(){
        List<Attachment> attachmentList = imageMapper.getAllImage();
        return attachmentList;
    }

}
