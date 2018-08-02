package com.hy.spring.cloud.wish.web;

import com.hy.spring.cloud.wish.domain.Wish;
import com.hy.spring.cloud.wish.domain.Message;
import com.hy.spring.cloud.wish.service.WishService;
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
@RequestMapping("/wish")
public class WishController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WishService wishService;

    @GetMapping("/queryWishList")
    public List<Wish> queryWishList(Map query){
        return wishService.queryWishList(query);
    }

    @PostMapping("/saveWish")
    public Message saveWish(@RequestBody Wish wish){
        return wishService.saveWish(wish);
    }

    @GetMapping(value="/delWish")
    public void delWish(Map map){
        try{
            wishService.removeWish(map);
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }
    }

}
