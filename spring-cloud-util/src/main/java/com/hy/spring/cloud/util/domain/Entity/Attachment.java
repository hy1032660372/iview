package com.hy.spring.cloud.util.domain.Entity;

/**
 * FileName: Attachment
 * Author:   hy103
 * Date:     2018/10/4 21:20
 * Description: attachment entity
 */
public class Attachment {

    private String id;

    private String fileName;

    private String pathUrl;

    private String fileType;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPathUrl() {
        return pathUrl;
    }

    public void setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
