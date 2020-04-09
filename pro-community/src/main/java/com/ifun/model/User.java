package com.ifun.model;

import lombok.Data;

/**
 * Create by iFun on 2020/03/31
 */
@Data
public class User {
    private Integer id;
    private String accountId;
    private String name;
    private String nickname;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String bio;
    private String avatarUrl;
}
