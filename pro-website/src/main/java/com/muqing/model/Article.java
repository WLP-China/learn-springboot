package com.muqing.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Create by iFun on 2020/07/15
 */
public class Article extends BaseEntity<Long> {
    private static final long serialVersionUID = 1796308333699180261L;

    private String type; //分类
    private String title; //标题
    private String content; //内容
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publishTime; //发布时间
    private Integer isTop; //1：置顶，0：不置顶
    private Integer status; //0：待发布，1：已发布
    private Integer readCount; //阅读数

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }
}
