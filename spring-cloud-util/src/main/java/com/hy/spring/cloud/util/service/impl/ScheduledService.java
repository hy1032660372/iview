package com.hy.spring.cloud.util.service.impl;

import com.hy.spring.cloud.util.domain.Entity.Attachment;
import com.hy.spring.cloud.util.service.UtilService;
import com.hy.spring.cloud.util.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${uploadFile}")
    private String filePath;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Async
    @Scheduled(cron = "0 0/10 * * * *")
    public void removeNoUseFile(){
        File f;
        String fullPath = filePath;
        if(System.getProperty("os.name").toLowerCase().startsWith("win")){
            fullPath = "D:/test" + fullPath;
        }

        //query no use file list
        Attachment Attachment = new Attachment();
        Attachment.setFileStatus(0);
        List<Attachment> attachmentList = utilService.queryAttachmentList(Attachment);
        for(Attachment attachment:attachmentList){
            f = new File(fullPath + attachment.getPathUrl() + attachment.getFileName());
            if(f.exists()){
                f.delete();
            }
        }
        utilService.deleteFileList(attachmentList);

        //delete temp file
        FileUtil.delAllFile(fullPath + "/temp");
        FileUtil.delBlankFolder(fullPath);
        logger.info("Remove file");
    }

}
