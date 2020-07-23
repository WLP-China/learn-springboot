package com.muqing.dto;

/**
 * 订单-发货
 * Create by iFun on 2020/06/30
 */
public class OrderSellerDTO {

    private String orderId; //订单ID
    private Double numSend; //发货量
    private Contact pJianzhan; //监站人员
    private Contact pDriver; //司机

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getNumSend() {
        return numSend;
    }

    public void setNumSend(Double numSend) {
        this.numSend = numSend;
    }

    public Contact getpJianzhan() {
        return pJianzhan;
    }

    public void setpJianzhan(Contact pJianzhan) {
        this.pJianzhan = pJianzhan;
    }

    public Contact getpDriver() {
        return pDriver;
    }

    public void setpDriver(Contact pDriver) {
        this.pDriver = pDriver;
    }
}
