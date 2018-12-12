package com.hy.spring.cloud.util.service.impl;

import com.hy.spring.cloud.util.domain.Entity.Attachment;
import com.hy.spring.cloud.util.domain.Entity.Message;
import com.hy.spring.cloud.util.mapper.UtilMapper;
import com.hy.spring.cloud.util.service.UtilService;
import com.hy.spring.cloud.util.util.ExcelUtil;
import com.hy.spring.cloud.util.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

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

    public Message uploadAttachment(MultipartFile file){

        Attachment attachment = new Attachment();

        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        if (suffixName.matches("png|jpg|jpeg|gif")){
            //images
            FileUtil.transferTo(file, attachment);
        }
        if(suffixName.matches("xls|xlsx")){
            //FileUtil.singleSave(file, attachment);

            //analyse excel
            ExcelUtil.importExcel(file);
        }
        return Message.info(attachment);
    }

    @Override
    public Message saveFileList(List<Attachment> attachmentList){

        for(Attachment attachment:attachmentList){
            FileUtil.copyFile(FileUtil.filePath+FileUtil.pathUrl+"/"+attachment.getFileName(),
                    FileUtil.filePath+attachment.getPathUrl()+"/"+attachment.getFileName());
        }

        utilMapper.saveFileList(attachmentList);
        return Message.info("Success");
    }

    @Override
    public void fileDownLoad(HttpServletResponse response, String fileId) {

        //获取文件名
        Attachment attachment = utilMapper.getAttachment(fileId);
        File file = new File(FileUtil.filePath + attachment.getPathUrl()+"/"+attachment.getFileName());
        FileUtil.downloadFile(response,file,attachment.getFileName());

    }

    @Override
    public Message deleteFileList(List<Attachment> attachmentList) {
        if(attachmentList != null && attachmentList.size() > 0){
            utilMapper.deleteFileList(attachmentList);
        }
        return Message.info("Success");
    }

    @Override
    public List<Attachment> queryAttachmentList(Attachment attachment) {
        return utilMapper.queryAttachmentList(attachment);
    }

}
