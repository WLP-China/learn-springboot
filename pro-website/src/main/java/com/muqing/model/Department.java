package com.muqing.model;

/**
 * Create by iFun on 2020/07/20
 */
public class Department extends BaseEntity<Long>{
    private static final long serialVersionUID = 8475817878500659109L;

    private String name;
    private String info;
    private String isTop;
    private String img;
    private Integer sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
