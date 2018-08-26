package com.hy.spring.cloud.account.util;

import com.hy.spring.cloud.account.domain.SysRoleImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TreeUtil
 * @Description TODO
 * @Author P1316771
 * @Date 8/3/2018
 **/
public class TreeUtil {

    /**
     * 将List转化成Tree
     * @param nodeList
     * @param node
     */
    public static<T extends TreeData<T>> void converseToTree(List<T> nodeList, T node){
        List<T> children = new ArrayList<>();
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

    /**
     * 将树转化成List
     * @param list
     * @param t
     */
    public static<T extends TreeData<T>> void converseToList(List<T> list, T t){
        list.add(t);
        T current;
        List<T> childrenList = t.getChildren();
        for(int i = 0; i < childrenList.size(); i++){
            current = childrenList.get(i);
            converseToList(list,current);
        }
    }
}
