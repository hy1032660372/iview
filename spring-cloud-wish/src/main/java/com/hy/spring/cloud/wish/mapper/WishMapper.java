package com.hy.spring.cloud.wish.mapper;

import com.hy.spring.cloud.wish.domain.Wish;
import com.hy.spring.cloud.wish.util.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author as_hy
 */
@Mapper
public interface WishMapper extends MyMapper<Wish> {

    List<Wish> queryList(Map query);

    void saveWish(Wish wish);

    void removeWish(Map query);

}
