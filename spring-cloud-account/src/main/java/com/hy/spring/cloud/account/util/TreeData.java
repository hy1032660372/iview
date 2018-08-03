package com.hy.spring.cloud.auth.domain;

import java.util.List;

/**
 * @ClassName TreeData
 * @Description TODO
 * @Author P1316771
 * @Date 8/3/2018
 **/
public class TreeData<T> {

    private String code;

    private String parentCode;

    private List<TreeData<T>> children;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public List<TreeData<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeData<T>> children) {
        this.children = children;
    }
}
