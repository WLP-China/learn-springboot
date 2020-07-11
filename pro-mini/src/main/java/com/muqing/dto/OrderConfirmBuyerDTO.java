package com.muqing.dto;

/**
 * Create by iFun on 2020/07/11
 */
public class OrderConfirmBuyerDTO {
    private String orderId; //订单ID
    private String numReceive; //接收量
    private Contact pReceive; //接收人
    private Contact pPangzhanA; //旁站人

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getNumReceive() {
        return numReceive;
    }

    public void setNumReceive(String numReceive) {
        this.numReceive = numReceive;
    }

    public Contact getpReceive() {
        return pReceive;
    }

    public void setpReceive(Contact pReceive) {
        this.pReceive = pReceive;
    }

    public Contact getpPangzhanA() {
        return pPangzhanA;
    }

    public void setpPangzhanA(Contact pPangzhanA) {
        this.pPangzhanA = pPangzhanA;
    }
}
