package com.hy.spring.cloud.util.service;

import com.hy.spring.cloud.util.domain.Entity.Attachment;

import java.util.List;

public interface ImageService {

    String getImage(String fileId);

    List<Attachment> getAllImage();
}
