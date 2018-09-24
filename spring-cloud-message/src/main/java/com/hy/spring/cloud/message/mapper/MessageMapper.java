package com.hy.spring.cloud.message.mapper;

import com.hy.spring.cloud.message.util.MyMapper;
import com.hy.spring.cloud.message.domain.Entity.MessageBody;
import org.apache.ibatis.annotations.Mapper;

/**
 * FileName: MessageMapper
 * Author:   hy103
 * Date:     2018/9/24 22:20
 * Description: Message Mapper
 */

@Mapper
public interface MessageMapper extends MyMapper<MessageBody> {

}
