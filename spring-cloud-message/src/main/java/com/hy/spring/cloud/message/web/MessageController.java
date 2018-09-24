package com.hy.spring.cloud.message.web;

import com.hy.spring.cloud.message.domain.Entity.Message;
import com.hy.spring.cloud.message.domain.Entity.MessageBody;
import com.hy.spring.cloud.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * FileName: MessageController
 * Author:   hy103
 * Date:     2018/8/15 22:07
 * Description: Message
 */

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * add new message
     * @param messsage
     * @return Message
     */
    @PreAuthorize("hasAuthority('USER_ADD')")
    @RequestMapping(value = "insertAccount/{roleCode}", method = RequestMethod.POST)
    public Message insertUser(@PathVariable String roleCode, @RequestBody MessageBody messsage) {
        return Message.info("Success");
    }

}
