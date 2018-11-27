package com.hy.spring.cloud.util.web;

import com.hy.spring.cloud.util.domain.Entity.Attachment;
import com.hy.spring.cloud.util.domain.Entity.Message;
import com.hy.spring.cloud.util.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * FileName: FileController
 * Author:   hy103
 * Date:     2018/10/22 21:44
 * Description: file controller
 */

@RestController
@RequestMapping("/upload")
public class FileController {

    @Autowired
    private UtilService utilService;

    /**
     * add new message
     * @param file
     * @return Message
     */
    //@PreAuthorize("hasAuthority('FILE_ADD')")
    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Message upload(@RequestParam("file") MultipartFile file) {
        return utilService.uploadAttachment(file);
    }

    /**
     * add new message
     * @param attachmentList
     * @return Message
     */
    //@PreAuthorize("hasAuthority('FILE_ADD')")
    @RequestMapping(value = "saveFileList", method = RequestMethod.POST)
    @ResponseBody
    public Message saveFileList(@RequestBody List<Attachment> attachmentList) {
        return utilService.saveFileList(attachmentList);
    }

    /**
     * add new message
     * @param
     * @return Message
     */
    //@PreAuthorize("hasAuthority('FILE_ADD')")
    @RequestMapping(value = "test", method = RequestMethod.GET)
    @ResponseBody
    public Message test() {
        return Message.info("aaaaaaaaaaa");
    }

}
