package com.hy.spring.cloud.util.web;

import com.hy.spring.cloud.util.domain.Entity.Attachment;
import com.hy.spring.cloud.util.domain.Entity.Message;
import com.hy.spring.cloud.util.service.ImageService;
import com.hy.spring.cloud.util.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * FileName: imageController
 * Author:   hy103
 * Date:     2018/11/28 22:05
 * Description: image controller
 */

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    /**
     * add new message
     * @param
     * @return Message
     */
    //@PreAuthorize("hasAuthority('FILE_ADD')")
    @ResponseBody
    @RequestMapping(value = "{fileId}", method = RequestMethod.GET)
    public String getImage(@PathVariable String fileId) {
        return imageService.getImage(fileId);
    }

    /**
     * add new message
     * @param
     * @return Message
     */
    //@PreAuthorize("hasAuthority('FILE_ADD')")
    @ResponseBody
    @RequestMapping(value = "getAllImage", method = RequestMethod.GET)
    public List<Attachment> getAllImage() {
        return imageService.getAllImage();
    }

}
