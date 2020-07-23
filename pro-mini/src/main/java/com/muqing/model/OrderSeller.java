package com.muqing.model;

/**
 * 发货单
 * Create by iFun on 2020/07/23
 */
public class OrderSeller extends BaseEntity<Long>{
    private static final long serialVersionUID = -5120023503138896251L;

    private Long orderId;//订单ID
    private Integer no;//单号
    private Double numSend;//发货量
    private String pJianzhan;//监站人员
    private String pDriver;//司机

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Double getNumSend() {
        return numSend;
    }

    public void setNumSend(Double numSend) {
        this.numSend = numSend;
    }

    public String getpJianzhan() {
        return pJianzhan;
    }

    public void setpJianzhan(String pJianzhan) {
        this.pJianzhan = pJianzhan;
    }

    public String getpDriver() {
        return pDriver;
    }

    public void setpDriver(String pDriver) {
        this.pDriver = pDriver;
    }
}
