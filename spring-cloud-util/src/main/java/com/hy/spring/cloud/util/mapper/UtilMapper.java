package com.hy.spring.cloud.util.mapper;

import com.hy.spring.cloud.util.util.MyMapper;
import com.hy.spring.cloud.util.domain.Entity.Attachment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * FileName: MessageMapper
 * Author:   hy103
 * Date:     2018/9/24 22:20
 * Description: Message Mapper
 */

@Mapper
public interface UtilMapper extends MyMapper<Attachment> {

    void saveFileList(@Param("attachmentList") List<Attachment> attachmentList);

    void deleteFileList(@Param("attachmentList") List<Attachment> attachmentList);

}
