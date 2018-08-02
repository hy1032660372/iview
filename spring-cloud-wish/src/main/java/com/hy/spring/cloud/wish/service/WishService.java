package com.hy.spring.cloud.wish.service;

import com.hy.spring.cloud.wish.domain.Message;
import com.hy.spring.cloud.wish.domain.Wish;

import java.util.List;
import java.util.Map;

/**
 * @author as_hy
 */
public interface WishService {

    List<Wish> queryWishList(Map query);

    Message saveWish(Wish wish);

    void removeWish(Map map);
}
