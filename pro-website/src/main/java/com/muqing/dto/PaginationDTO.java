package com.muqing.dto;

import java.util.List;

/**
 * 简单分页对象 （for微信小程序使用）
 * Create by iFun on 2020/07/10
 */
public class PaginationDTO<T> {
    private List<T> data;
    private Integer totalPage;//总页数
    private Integer currentPage;//当前页

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}
