package com.hy.spring.cloud.account.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pagehelper.PageInfo;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SimplePage<T> implements Serializable {

    private int totalPages;
    @JsonProperty("iTotalRecords")
    private long totalElements;
    private int number;
    private int size = 20;
    @JsonProperty("aaData")
    private List<T> content = new ArrayList();
    private boolean hasContent = false;
    private boolean isFirst = true;
    private boolean isLast = true;

    public SimplePage() {
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public boolean isHasContent() {
        return hasContent;
    }

    public void setHasContent(boolean hasContent) {
        this.hasContent = hasContent;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public SimplePage<T> convert(PageInfo<T> page) {
        SimplePage<T> simplePage = new SimplePage();
        if (page != null) {
            simplePage.setTotalPages(page.getPages());
            simplePage.setTotalElements(page.getTotal());
            simplePage.setNumber(page.getPageNum());
            simplePage.setSize(page.getSize());
            simplePage.setContent(page.getList());
            simplePage.setHasContent(!CollectionUtils.isEmpty(page.getList()));
            simplePage.setFirst(page.isIsFirstPage());
            simplePage.setLast(page.isIsLastPage());
        }

        return simplePage;
    }
}
