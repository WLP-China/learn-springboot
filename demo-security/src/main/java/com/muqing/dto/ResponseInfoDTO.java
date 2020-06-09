package com.muqing.dto;

import java.io.Serializable;

public class ResponseInfoDTO implements Serializable {
    private static final long serialVersionUID = -4417715614021482064L;

    private String code;
    private String message;

    public ResponseInfoDTO(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
