package com.muqing.model;

/**
 * Create by iFun on 2020/07/21
 */
public class Nurse extends BaseEntity<Long>{
    private static final long serialVersionUID = 8682186680443008552L;

    private String name;// 姓名
    private String photo;// 照片
    private String professionalTitle;// 职称
    private String info;// 简介
    private Integer sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
