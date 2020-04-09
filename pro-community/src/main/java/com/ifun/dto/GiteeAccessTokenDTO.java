package com.ifun.dto;

import lombok.Data;

/**
 * Create by iFun on 2020/04/01
 */
@Data
public class GiteeAccessTokenDTO {
    public String grant_type;
    public String client_id;
    public String client_secret;
    public String code;
    public String redirect_uri;
}
