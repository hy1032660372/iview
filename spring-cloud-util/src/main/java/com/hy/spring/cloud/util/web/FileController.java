package com.hy.spring.cloud.util.web;

import com.hy.spring.cloud.util.domain.Entity.Attachment;
import com.hy.spring.cloud.util.domain.Entity.Message;
import com.hy.spring.cloud.util.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * FileName: FileController
 * Author:   hy103
 * Date:     2018/10/22 21:44
 * Description: file controller
 */

@RestController
@RequestMapping("/util")
public class FileController {

    @Autowired
    private UtilService utilService;

    /**
     * add new message
     * @param attachment
     * @return Message
     */
    //@PreAuthorize("hasAuthority('FILE_ADD')")
    @RequestMapping(value = "upload/{roleCode}", method = RequestMethod.POST)
    public Message upload(@PathVariable String roleCode, @RequestBody Attachment attachment) {
        return utilService.uploadAttachment(attachment);
    }

}
