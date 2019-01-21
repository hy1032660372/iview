package com.hy.spring.cloud.util.web;

import com.hy.spring.cloud.util.domain.Entity.Attachment;
import com.hy.spring.cloud.util.domain.Entity.Message;
import com.hy.spring.cloud.util.service.UtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * FileName: FileController
 * Author:   hy103
 * Date:     2018/10/22 21:44
 * Description: file controller
 */

@RestController
@RequestMapping("/file")
public class FileController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UtilService utilService;

    /**
     * add new message
     * @param fileList
     * @return Message
     */
    //@PreAuthorize("hasAuthority('FILE_ADD')")
    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Message upload(@RequestParam("file") MultipartFile fileList) {
        return utilService.uploadAttachment(fileList);
    }

    /**
     * save file
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
     * remove file
     * @param attachmentList
     * @return
     */
    @RequestMapping(value = "deleteFileList", method = RequestMethod.POST)
    @ResponseBody
    public Message deleteFileList(@RequestBody List<Attachment> attachmentList){
        return utilService.deleteFileList(attachmentList);
    }

    /**
     * remove file
     * @param fileId
     * @return
     */
    @RequestMapping(value = "deleteFile/{fileId}", method = RequestMethod.GET)
    @ResponseBody
    public Message deleteFile(@PathVariable String fileId){
        return utilService.deleteFile(fileId);
    }

    /**
     * remove file
     * @param attachment
     * @return
     */
    @RequestMapping(value = "queryAttachmentList", method = RequestMethod.POST)
    @ResponseBody
    public List<Attachment> queryAttachmentList(@RequestBody Attachment attachment){
        return utilService.queryAttachmentList(attachment);
    }
}
