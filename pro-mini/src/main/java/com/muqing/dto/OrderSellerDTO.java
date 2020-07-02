package com.muqing.dto;

/**
 * 订单-发货
 * Create by iFun on 2020/06/30
 */
public class OrderSellerDTO {

    private String sellerEnterpriseId; //单位ID-供方
    private String numCend; //发货量
    private String pJianzhan; //监站人员
    private String pDirver; //司机

    public String getSellerEnterpriseId() {
        return sellerEnterpriseId;
    }

    public void setSellerEnterpriseId(String sellerEnterpriseId) {
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
}
