package com.hy.spring.cloud.util.web;

import com.hy.spring.cloud.util.domain.Entity.Attachment;
import com.hy.spring.cloud.util.domain.Entity.Message;
import com.hy.spring.cloud.util.service.UtilService;
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
@RequestMapping("/util")
public class MessageController {

    @Autowired
    private UtilService utilService;

    /**
     * add new message
     * @param attachment
     * @return Message
     */
    @PreAuthorize("hasAuthority('USER_ADD')")
    @RequestMapping(value = "upload/{roleCode}", method = RequestMethod.POST)
    public Message upload(@PathVariable String roleCode, @RequestBody Attachment attachment) {
        return utilService.uploadAttachment(attachment);
    }

}
