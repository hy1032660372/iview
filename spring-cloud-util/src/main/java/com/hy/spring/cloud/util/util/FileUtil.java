package com.hy.spring.cloud.util.util;

import com.hy.spring.cloud.message.util.UUIDUtil;
import com.hy.spring.cloud.util.domain.Entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @ClassName fileUtil
 * @Description TODO
 * @Author P1316771
 * @Date 12/11/2018
 **/
public class FileUtil {

    // 文件上传后的路径
    public static String pathUrl = "/temp";

    /**
     * 删除文件夹里面的所有文件
     *
     * @param path String  文件夹路径
     */
    public static void delAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            file.delete();
            return;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + File.separator + tempList[i]);//先删除文件夹里面的文件
            }
        }
    }


    /**
     * delete blank Folder
     * @param path
     */
    public static void delBlankFolder(String path){
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }

        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            temp = new File(path + File.separator + tempList[i]);
            if(temp.length()==0) {
                temp.delete();
            }
        }
    }

    /**
     * copy file
     * @param fromPath
     * @param toPath
     */
    public static void copyFile(String fromPath, String toPath){

        File fromFile = new File(fromPath);
        File toFile = new File(toPath);

        if (!toFile.getParentFile().exists()) {
            toFile.getParentFile().mkdirs();
        }

        InputStream input = null;
        OutputStream output = null;

        try{
            input = new FileInputStream(fromFile);
            output = new FileOutputStream(toFile);

            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }

            input.close();
            output.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //transferTo
    public static void transferTo(MultipartFile file, Attachment attachment,String path) {
        try {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));

            String fullPath = path + pathUrl;
            // 解决中文问题，liunx下中文路径，图片显示问题
            // fileName = UUIDUtil.createUUID() + suffixName;

            File dest = new File(fullPath + "/" + fileName);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);

            attachment.setId(UUIDUtil.createUUID());
            attachment.setFileType("temp");
            attachment.setFileName(fileName);
            attachment.setPathUrl(pathUrl);
            attachment.setFileStatus(0);
            attachment.setSuffixName(suffixName);

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //singleSave
    public static void singleSave(MultipartFile file, Attachment attachment,String path){

        String fullPath = path + pathUrl;
        if(System.getProperty("os.name").toLowerCase().startsWith("win")){
            fullPath = "D:/test" + fullPath ;
        }

        String fileName = file.getOriginalFilename();
        File dest = new File(fullPath + File.separator + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {

            byte[] bytes = file.getBytes();
            BufferedOutputStream buffStream =
                    new BufferedOutputStream(new FileOutputStream(dest));
            buffStream.write(bytes);
            buffStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        attachment.setId(UUIDUtil.createUUID());
        attachment.setFileType("temp");
        attachment.setFileName(fileName);
        attachment.setPathUrl(pathUrl);
    }

    //multipleSave
    public void multipleSave(MultipartFile[] fileList, List<Attachment> attachmentList,String path){
        String fileName = null;
        Attachment attachment;

        String fullPath = path + pathUrl;
        if(System.getProperty("os.name").toLowerCase().startsWith("win")){
            fullPath = "D:/test" + fullPath ;
        }

        if (fileList != null && fileList.length >0) {
            for(int i =0 ;i< fileList.length; i++){
                attachment = new Attachment();
                try {
                    fileName = fileList[i].getOriginalFilename();
                    byte[] bytes = fileList[i].getBytes();
                    BufferedOutputStream buffStream =
                            new BufferedOutputStream(new FileOutputStream(new File(fullPath + "/" + fileName)));
                    buffStream.write(bytes);
                    buffStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                attachment.setId(UUIDUtil.createUUID());
                attachment.setFileType("temp");
                attachment.setFileName(fileName);
                attachment.setPathUrl(pathUrl);
                attachmentList.add(attachment);
            }
        }
    }

    /**
     * downLoad file
     * @param response
     * @param file
     * @param fileName
     */
    public static void downloadFile(HttpServletResponse response, File file, String fileName){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try{
            //获取文件的长度
            long fileLength = file.length();

            //设置文件输出类型
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            //设置输出长度
            response.setHeader("Content-Length", String.valueOf(fileLength));
            //获取输入流
            bis = new BufferedInputStream(new FileInputStream(file));
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
            e.printStackTrace();
        }
    }
}
