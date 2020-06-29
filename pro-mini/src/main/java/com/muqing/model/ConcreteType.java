package com.muqing.model;

/**
 * 混凝土类型
 */
public class ConcreteType extends BaseEntity<Long> {
    private static final long serialVersionUID = 6378749647357402540L;

    private String type;
    private String k;
    private String val;
    private Double money;
    private Integer sort;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
