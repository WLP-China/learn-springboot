package com.muqing.dto;

/**
 * Create by iFun on 2020/07/11
 */
public class OrderConfirmSellerDTO {
    private String orderId; //订单ID
    private Contact pPangzhanB; //旁站人

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Contact getpPangzhanB() {
        return pPangzhanB;
    }

    public void setpPangzhanB(Contact pPangzhanB) {
        this.pPangzhanB = pPangzhanB;
    }
}
