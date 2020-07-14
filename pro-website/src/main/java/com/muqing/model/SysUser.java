package com.muqing.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SysUser extends BaseEntity<Long> {
    private static final long serialVersionUID = -6525908145032868837L;

    private String username;
    private String password;
    private String name;
    private String phone;
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private Date birthday;
    private Integer status;

    public interface Status {
        int DISABLED = 0;
        int VALID = 1;
        int LOCKED = 2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
