package com.hy.spring.cloud.account.util;

import java.util.List;

/**
 * @ClassName TreeData
 * @Author P1316771
 * @Date 8/3/2018
 **/
public class TreeData<T> {

    private String code;

    private String parentCode;

    private List<T> children;

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

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }
}
