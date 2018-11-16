package com.hy.spring.cloud.util.service.impl;

import com.hy.spring.cloud.util.domain.Entity.Attachment;
import com.hy.spring.cloud.util.domain.Entity.Message;
import com.hy.spring.cloud.util.mapper.UtilMapper;
import com.hy.spring.cloud.util.service.UtilService;
import com.hy.spring.cloud.message.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
        //utilMapper.uploadAttachment(attachment);
        if (file.isEmpty()) {
            return Message.info("文件为空");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件上传后的路径
        String filePath = "E://test//";
        // 解决中文问题，liunx下中文路径，图片显示问题 //
        fileName = UUIDUtil.createUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return Message.info("上传成功");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Message.info("上传失败");
    }
}
