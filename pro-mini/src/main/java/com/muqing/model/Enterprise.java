package com.muqing.model;

/**
 * 企业
 * Create by iFun on 2020/06/28
 */
public class Enterprise extends BaseEntity<Long> {
    private static final long serialVersionUID = -863771584519353896L;

    private String eName;//企业名称
    private String eInfo;//企业信息
    private String creditCode;//统一社会信用代码/税号
    private String addr;//单位地址
    private String telephone;//电话号码
    private Integer status;//状态

    public interface Status {
        int DISABLED = 0;
        int VALID = 1;
        int LOCKED = 2;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String geteInfo() {
        return eInfo;
    }

    public void seteInfo(String eInfo) {
        this.eInfo = eInfo;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
