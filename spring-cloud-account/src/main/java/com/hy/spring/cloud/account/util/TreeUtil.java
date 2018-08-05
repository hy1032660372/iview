package com.hy.spring.cloud.account.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TreeUtil
 * @Description TODO
 * @Author P1316771
 * @Date 8/3/2018
 **/
public class TreeUtil {

    public static<T extends TreeData<T>> void converseToTree(List<T> nodeList, T node){
        List<TreeData<T>> children = new ArrayList<>();
        T t;
        for(int i = 0; i < nodeList.size(); i++){
            t = nodeList.get(i);
            if(node.getCode().equals(t.getParentCode())){
                children.add(t);
                converseToTree(nodeList,t);
            }
        }
        node.setChildren(children);
    }
}
