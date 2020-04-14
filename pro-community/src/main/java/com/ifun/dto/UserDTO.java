package com.ifun.dto;

import lombok.Data;

/**
 * Create by iFun on 2020/03/30
 */
@Data
public class UserDTO {
    private Long id;
    private String name;
    private String bio;//描述
    private String avatarUrl;//头像地址
}
