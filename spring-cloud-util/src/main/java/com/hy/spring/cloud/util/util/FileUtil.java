package com.hy.spring.cloud.util.util;

import java.io.*;

/**
 * @ClassName fileUtil
 * @Description TODO
 * @Author P1316771
 * @Date 12/11/2018
 **/
public class FileUtil {

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
            e.getMessage();
        }
    }
}
