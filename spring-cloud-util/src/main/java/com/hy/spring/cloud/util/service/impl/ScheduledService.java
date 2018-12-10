package com.hy.spring.cloud.util.service.impl;

import com.hy.spring.cloud.util.domain.Entity.Attachment;
import com.hy.spring.cloud.util.service.UtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * @ClassName ScheduledService
 * @Description TODO
 * @Author P1316771
 * @Date 12/10/2018
 **/

@Component
public class ScheduledService {

    @Autowired
    private UtilService utilService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Async
    @Scheduled(cron = "0 0/5 * * * *")
    public void removeNoUseFile(){

        String filePath = "D:/test/";

        //query no use file list
        Attachment Attachment = new Attachment();
        Attachment.setFileType("test");
        List<Attachment> attachmentList = utilService.queryAttachmentList(Attachment);

        for(Attachment attachment:attachmentList){
            File f = new File(filePath + attachment.getPathUrl());
            if(f.exists()){
                f.delete();
            }
        }

        utilService.deleteFileList(attachmentList);
        logger.info("Remove file");
    }

}
