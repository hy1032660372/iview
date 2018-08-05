package com.hy.spring.cloud.account.util;

import java.util.UUID;

/**
 * FileName: UUIDUtil
 * Author:   hy103
 * Date:     2018/8/3 22:30
 * Description: create UUID
 */
public class UUIDUtil {

    public static String createUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
