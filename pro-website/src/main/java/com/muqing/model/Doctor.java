package com.muqing.model;

/**
 * 医生
 * Create by iFun on 2020/07/21
 */
public class Doctor extends BaseEntity<Long> {
    private static final long serialVersionUID = -5619010754017860747L;

    private String name;// 姓名
    private String photo;// 照片
    private String professionalTitle;// 职称
    private Long deparimentId;// 科室
    private String departmentName;
    private String major;// 专业特长
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

    public Long getDeparimentId() {
        return deparimentId;
    }

    public void setDeparimentId(Long deparimentId) {
        this.deparimentId = deparimentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
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
