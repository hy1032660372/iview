package com.hy.spring.cloud.wish.service.impl;

import com.hy.spring.cloud.wish.domain.Wish;
import com.hy.spring.cloud.wish.mapper.WishMapper;
import com.hy.spring.cloud.wish.domain.Message;
import com.hy.spring.cloud.wish.service.WishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author as_hy
 */
@Service
@Transactional
public class WishServiceImpl implements WishService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private WishMapper wishMapper;

    public List<Wish> queryWishList(Map query){
        List<Wish> list = wishMapper.queryList(query);
        return list;
    }

    public Message saveWish(Wish wish){
        wish.setId(UUID.randomUUID().toString().replace("-",""));
        wish.setAddTime(new Date());
        wish.setColor((int)(Math.random() * 5 + 1));
        if(wish.getName().length() == 0){
            return Message.warn("请输入标题");
        }
        if(wish.getName().length() > 12){
            return Message.warn("标题长度不超过12位");
        }
        try{
            wishMapper.saveWish(wish);
        }
        catch(Exception e){
            logger.error(e.getMessage());
            return Message.info("失败");
        }
        return Message.info("成功");
    }

    public void removeWish(Map map){
        wishMapper.removeWish(map);
    }

}
