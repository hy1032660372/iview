package com.hy.spring.cloud.auth.util;

import com.hy.spring.cloud.auth.domain.RoleData;
import com.hy.spring.cloud.auth.domain.TreeData;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TreeUtil
 * @Description TODO
 * @Author P1316771
 * @Date 8/3/2018
 **/
public class TreeUtil {

    public static<T extends TreeData<T>> void recursive(List<T> nodeList, T node){
        List<TreeData<T>> children = new ArrayList<>();
        T t;
        for(int i = 0; i < nodeList.size(); i++){
            t = nodeList.get(i);
            if(node.getCode().equals(t.getParentCode())){
                children.add(t);
                recursive(nodeList,t);
            }
        }
        node.setChildren(children);
    }
}
