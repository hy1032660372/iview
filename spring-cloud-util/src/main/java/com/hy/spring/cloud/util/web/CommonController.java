package com.hy.spring.cloud.util.web;

import com.hy.spring.cloud.util.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * FileName: CommonController
 * Author:   hy103
 * Date:     2018/12/16 13:09
 * Description: no auth
 */

@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private UtilService utilService;

    /**
     * add new message
     * @param response
     * @return Message
     */
    //@PreAuthorize("hasAuthority('FILE_DOWNLOAD')")
    @RequestMapping(value = "fileDownLoad", method = RequestMethod.GET)
    @ResponseBody
    public void fileDownLoad(HttpServletResponse response, String fileId) {
        utilService.fileDownLoad(response,fileId);
    }

}
