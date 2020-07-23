package com.muqing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 订单
 * Create by iFun on 2020/06/30
 */
public class OrderDTO {

    private Long buyerEnterpriseId; //单位ID-需方
    private String buyerEnterprise; //单位ID-需方
    private String proname; //工程名称
    private String position; //施工部位
    private String nunOrder; //订单量(预计用量)
    private String concreteAdditive; //添加剂
    private String concreteType; //混凝土型号
    private String concreteLevel; //抗渗压等级
    private String pumpingType; //泵送方式
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date sendTime; //发货时间、浇筑时间
    private Integer isFinance; //是否财政项目
    private String payType; //结算方式

    public Long getBuyerEnterpriseId() {
        return buyerEnterpriseId;
    }

    public void setBuyerEnterpriseId(Long buyerEnterpriseId) {
        this.buyerEnterpriseId = buyerEnterpriseId;
    }

    public String getBuyerEnterprise() {
        return buyerEnterprise;
    }

    public void setBuyerEnterprise(String buyerEnterprise) {
        this.buyerEnterprise = buyerEnterprise;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNunOrder() {
        return nunOrder;
    }

    public void setNunOrder(String nunOrder) {
        this.nunOrder = nunOrder;
    }

    public String getConcreteAdditive() {
        return concreteAdditive;
    }

    public void setConcreteAdditive(String concreteAdditive) {
        this.concreteAdditive = concreteAdditive;
    }

    public String getConcreteType() {
        return concreteType;
    }

    public void setConcreteType(String concreteType) {
        this.concreteType = concreteType;
    }

    public String getConcreteLevel() {
        return concreteLevel;
    }

    public void setConcreteLevel(String concreteLevel) {
        this.concreteLevel = concreteLevel;
    }

    public String getPumpingType() {
        return pumpingType;
    }

    public void setPumpingType(String pumpingType) {
        this.pumpingType = pumpingType;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getIsFinance() {
        return isFinance;
    }

    public void setIsFinance(Integer isFinance) {
        this.isFinance = isFinance;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
