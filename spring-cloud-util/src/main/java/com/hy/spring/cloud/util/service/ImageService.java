package com.hy.spring.cloud.util.service;

import com.hy.spring.cloud.util.domain.Entity.Message;

public interface ImageService {

    Message getImage(String fileId);

}
