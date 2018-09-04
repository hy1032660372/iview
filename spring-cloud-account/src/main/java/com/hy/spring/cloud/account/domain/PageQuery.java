package com.hy.spring.cloud.account.domain;

import com.google.common.base.Splitter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class PageQuery {

    private int page;

    private int size;

    private String filter;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Map<String, String> convertFilterToMap() {
        if (StringUtils.isEmpty(this.filter)) {
            return null;
        } else {
            List<String> list = Splitter.on("&").omitEmptyStrings().trimResults().splitToList(this.filter);
            if (CollectionUtils.isEmpty(list)) {
                return null;
            } else {
                Map<String, String> map = new HashMap();
                Iterator var3 = list.iterator();

                while(var3.hasNext()) {
                    String s = (String)var3.next();
                    List<String> item = Splitter.on("=").limit(2).omitEmptyStrings().trimResults().splitToList(s);
                    if (item.size() == 2) {
                        map.put(item.get(0), item.get(1));
                    }
                }

                return map;
            }
        }
    }
}
