package com.ifun.dto;

import lombok.Data;

/**
 * Create by iFun on 2020/03/30
 */
@Data
public class AccessTokenDTO {
    public String client_id;
    public String client_secret;
    public String code;
    public String redirect_uri;
    public String state;
}
