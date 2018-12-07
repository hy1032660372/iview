package com.hy.spring.cloud.util.mapper;

import com.hy.spring.cloud.util.domain.Entity.Attachment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ImageMapper {

    Attachment getImage(@Param("fileId") String fileId);

}
