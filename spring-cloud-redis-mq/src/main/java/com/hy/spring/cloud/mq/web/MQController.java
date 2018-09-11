package com.hy.spring.cloud.mq.web;

import com.hy.spring.cloud.mq.domain.Wish;
import com.hy.spring.cloud.mq.domain.Message;
import com.hy.spring.cloud.mq.service.MQService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author as_hy
 * @Copyright as_hy
 * @Description 许愿墙
 * @Date 2018年3月19日
 */
@Api(description = "许愿墙")
@RestController
@RequestMapping("/mq")
public class MQController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MQService mqService;

    @GetMapping("/query")
    public String query(Map query){
        return mqService.queryWishList(query);
    }

    @GetMapping(value = "pub/{id}")
    public String pubMsg(@PathVariable String id){
        return mqService.pubMsg(id);
    }
}
