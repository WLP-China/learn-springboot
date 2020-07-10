package com.muqing.model;

import java.util.Date;
/*
//id
buyer_enterprise
buyer_enterprise_id
proname
position
nun_order
concrete_additive
concrete_type
concrete_level
pumping_type
sendTime
is_finance
pay_type

seller_enterprise
seller_enterprise_id
num_cend
p_jianzhan
p_dirver

num_receive
p_receive
p_pangzhan_A
p_pangzhan_B
status

//createTime
//updateTime
*/
public class Order extends BaseEntity<Long> {
    private static final long serialVersionUID = 8000583932282918364L;

    private String buyerEnterprise;//单位名称-需方
    private Long buyerEnterpriseId;
    private String proname;//工程名称
    private String position;//施工部位
    private String nunOrder;//订单量(预计用量)
    private String concreteAdditive;//添加剂
    private String concreteType;//混凝土型号
    private String concreteLevel;//抗渗压等级
    private String pumpingType;//泵送方式
    private Date sendTime;//发货时间
    private Integer isFinance;//是否财政项目
    private String payType;//结算方式

    private String sellerEnterprise;//单位名称-供方
    private Long sellerEnterpriseId;
    private String numCend;//发货量
    private String pJianzhan;//监站人员
    private String pDirver;//司机

    private String numReceive;//接收量
    private String pReceive;//接收人
    private String pPangzhanA;//旁站人A-需方人员
    private String pPangzhanB;//旁站人B-供方人员

    private Integer status;

    public interface Status {
        int DAISHENHE = 1;
        int DAIFAHUO = 2;
        int YIFAHUO = 3;
        int YIQIANSHOU = 4;
        int YIWANCHENG = 5;
    }

    public String getBuyerEnterprise() {
        return buyerEnterprise;
    }

    public void setBuyerEnterprise(String buyerEnterprise) {
        this.buyerEnterprise = buyerEnterprise;
    }

    public Long getBuyerEnterpriseId() {
        return buyerEnterpriseId;
    }

    public void setBuyerEnterpriseId(Long buyerEnterpriseId) {
        this.buyerEnterpriseId = buyerEnterpriseId;
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

    public String getSellerEnterprise() {
        return sellerEnterprise;
    }

    public void setSellerEnterprise(String sellerEnterprise) {
        this.sellerEnterprise = sellerEnterprise;
    }

    public Long getSellerEnterpriseId() {
        return sellerEnterpriseId;
    }

    public void setSellerEnterpriseId(Long sellerEnterpriseId) {
        this.sellerEnterpriseId = sellerEnterpriseId;
    }

    public String getNumCend() {
        return numCend;
    }

    public void setNumCend(String numCend) {
        this.numCend = numCend;
    }

    public String getpJianzhan() {
        return pJianzhan;
    }

    public void setpJianzhan(String pJianzhan) {
        this.pJianzhan = pJianzhan;
    }

    public String getpDirver() {
        return pDirver;
    }

    public void setpDirver(String pDirver) {
        this.pDirver = pDirver;
    }

    public String getNumReceive() {
        return numReceive;
    }

    public void setNumReceive(String numReceive) {
        this.numReceive = numReceive;
    }

    public String getpReceive() {
        return pReceive;
    }

    public void setpReceive(String pReceive) {
        this.pReceive = pReceive;
    }

    public String getpPangzhanA() {
        return pPangzhanA;
    }

    public void setpPangzhanA(String pPangzhanA) {
        this.pPangzhanA = pPangzhanA;
    }

    public String getpPangzhanB() {
        return pPangzhanB;
    }

    public void setpPangzhanB(String pPangzhanB) {
        this.pPangzhanB = pPangzhanB;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}