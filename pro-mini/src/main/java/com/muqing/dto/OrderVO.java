package com.muqing.dto;

import java.util.Date;

/**
 * 订单VO 用于列表展示
 * Create by iFun on 2020/07/03
 */
public class OrderVO {
    private Long id;

    private Long buyerEnterpriseId; //单位ID-需方
    private String buyerEnterprise; //单位名称-需方
    private String sellerEnterprise; //单位ID-供方
    private String sellerEnterpriseId; //单位ID-供方

    private String proname; //工程名称
//    private String position; //施工部位
    private String nunOrder; //订单量(预计用量)
    private String concreteAdditive; //添加剂
    private String concreteType; //混凝土型号
    private String concreteLevel; //抗渗压等级
    private String pumpingtype; //泵送方式
    private Date sendTime; //发货时间
    private Boolean isFinance; //是否财政项目
    private String payType; //结算方式

    private Integer status;

    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getSellerEnterprise() {
        return sellerEnterprise;
    }

    public void setSellerEnterprise(String sellerEnterprise) {
        this.sellerEnterprise = sellerEnterprise;
    }

    public String getSellerEnterpriseId() {
        return sellerEnterpriseId;
    }

    public void setSellerEnterpriseId(String sellerEnterpriseId) {
        this.sellerEnterpriseId = sellerEnterpriseId;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
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

    public String getPumpingtype() {
        return pumpingtype;
    }

    public void setPumpingtype(String pumpingtype) {
        this.pumpingtype = pumpingtype;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Boolean getFinance() {
        return isFinance;
    }

    public void setFinance(Boolean finance) {
        isFinance = finance;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
