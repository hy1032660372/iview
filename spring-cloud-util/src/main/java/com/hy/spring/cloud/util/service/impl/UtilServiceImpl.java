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
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUIDUtil.createUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            Attachment attachment = new Attachment();
            attachment.setId(UUIDUtil.createUUID());
            attachment.setPathUrl(filePath+fileName);
            return Message.info(attachment);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Message.info("上传失败");
    }

    public Message saveFileList(List<Attachment> attachmentList){
        utilMapper.saveFileList(attachmentList);
        return Message.info("Success");
    }

    @Override
    public void fileDownLoad(HttpServletResponse response, String fileId) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        //获取下载文件路径
        String downLoadPath = "";

        //获取文件名
        Attachment attachment = utilMapper.getAttachment(fileId);

        //获取文件的长度
        long fileLength = new File(downLoadPath).length();

        try{
            //设置文件输出类型
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(attachment.getFileName().getBytes("utf-8"), "ISO8859-1"));
            //设置输出长度
            response.setHeader("Content-Length", String.valueOf(fileLength));
            //获取输入流
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            //输出流
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            //关闭流
            bis.close();
            bos.close();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
