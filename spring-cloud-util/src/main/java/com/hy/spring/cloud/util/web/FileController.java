package com.hy.spring.cloud.util.web;

import com.hy.spring.cloud.util.domain.Entity.Attachment;
import com.hy.spring.cloud.util.domain.Entity.Message;
import com.hy.spring.cloud.util.service.UtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
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

    private final ResourceLoader resourceLoader;

    @Autowired
    private UtilService utilService;

    @Autowired
    public FileController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * add new message
     * @param fileList
     * @return Message
     */
    //@PreAuthorize("hasAuthority('FILE_ADD')")
    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Message upload(@RequestParam("file") MultipartFile[] fileList) {
        return utilService.uploadAttachment(fileList);
    }

    /**
     * add new message
     * @param response
     * @return Message
     */
    //@PreAuthorize("hasAuthority('FILE_ADD')")
    @RequestMapping(value = "fileDownLoad", method = RequestMethod.POST)
    @ResponseBody
    public void fileDownLoad(HttpServletResponse response,String fileId) {
        utilService.fileDownLoad(response,fileId);
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
}
