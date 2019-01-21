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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Value("${uploadFile}")
    private String uploadPath;

    public Message uploadAttachment(MultipartFile file){

        Attachment attachment = new Attachment();

        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf(".")+1);
        if (suffixName.matches("png|jpg|jpeg|gif")){
            //images
            FileUtil.transferTo(file, attachment,uploadPath);
            return Message.info(attachment);
        }
        if(suffixName.matches("xls|xlsx")){
            //FileUtil.singleSave(file, attachment);
            //analyse excel
            List<Map> mapList = new ArrayList<>();
            ExcelUtil.importExcel(file,mapList);
            return Message.info(mapList);
        }
        return Message.info("");
    }

    @Override
    public Message saveFileList(List<Attachment> attachmentList){
        String path = uploadPath;
        for(Attachment attachment:attachmentList){
            FileUtil.copyFile(path + FileUtil.pathUrl+"/"+attachment.getFileName(),
                    path+attachment.getPathUrl()+"/"+attachment.getFileName());
        }

        utilMapper.saveFileList(attachmentList);
        return Message.info("Success");
    }

    @Override
    public void fileDownLoad(HttpServletResponse response, String fileId) {

        String fullPath = uploadPath;

        //获取文件名
        Attachment attachment = utilMapper.getAttachment(fileId);
        File file = new File(fullPath + attachment.getPathUrl()+"/"+attachment.getFileName());
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

    @Override
    public Message deleteFile(String fileId) {
        Attachment attachment = utilMapper.getAttachment(fileId);

        FileUtil.delAllFile(uploadPath+attachment.getPathUrl()+File.separator+attachment.getFileName());
        utilMapper.deleteFile(fileId);
        return Message.info("Success");
    }
}
